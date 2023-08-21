package webshop.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import webshop.contracts.FilterServiceContract;
import webshop.models.dto.SingleFilter;
import webshop.models.entities.AttributeEntity;
import webshop.models.responses.AttributeValueResponse;
import webshop.models.responses.ProductResponse;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class FilterService implements FilterServiceContract {

    private final ProductService productService;
    private final CategoryService categoryService;
    private final AttributeService attributeService;
    private final ProductAttributeService productAttributeService;

    public FilterService(ProductService productService, CategoryService categoryService, AttributeService attributeService, ProductAttributeService productAttributeService) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.attributeService = attributeService;
        this.productAttributeService = productAttributeService;
    }

    private List<ProductResponse> filterByCategory(String category) {
        return productService
                .findAll(ProductResponse.class)
                .stream()
                .filter(x -> {
                    String categoryName = categoryService.findCategoryById(x.getCategoryId()).getName();;
                    return categoryName.equals(category);
                }).collect(Collectors.toList());
    }

    @Override
    public Page<ProductResponse> filterByCategoryAndAttributes(String category,
                                                                  List<SingleFilter> filtersList,
                                                                  Pageable pageable,
                                                               Double minPrice,
                                                               Double maxPrice) {
        if(minPrice == null){
            minPrice = 0.0;
        }

        if(maxPrice == null){
            maxPrice = Double.MAX_VALUE;
        }
        Double finalMinPrice = minPrice;
        Double finalMaxPrice = maxPrice;
        List<ProductResponse> filteredByCategory = filterByCategory(category)
                .stream()
                .filter(x -> x.getPrice() >= finalMinPrice && x.getPrice() <= finalMaxPrice)
                .toList();

        HashSet<ProductResponse> response = new HashSet<>();

        // loop through all products and filters, check product with every filter
        for (ProductResponse product : filteredByCategory) {
            boolean passesAllFilters = true;
            for(SingleFilter filter : filtersList) {
                // TODO: find attribute values for product
                List<AttributeValueResponse> attributeValues =
                        productAttributeService.getAttributeValuesByProductId(product.getId());
                System.out.println(filter);
                String filterType = attributeService.getAttributeTypeByName(filter.getAttributeName());
                if(filterType.equals("Integer")){
                    passesAllFilters = filterIntegers(attributeValues, filter);
                } else if (filterType.equals("Double")) {
                    passesAllFilters = filterDouble(attributeValues, filter);
                } else {
                    passesAllFilters = filterString(attributeValues, filter);
                }

                if(!passesAllFilters) {
                    break;
                }
            }
            if (passesAllFilters) {
                response.add(product);
            }
        }
        return convertProductsToPageable(pageable, response.stream().toList());
    }

    private boolean filterIntegers(List<AttributeValueResponse> productAttributes, SingleFilter filter) {
        // filter productAttributes and use only integer and have single filter name
        AttributeValueResponse  singleAttributeValue = productAttributes
                .stream()
                .filter(x -> x.getType().equals("Integer")
                        && x.getAttributeName().equals(filter.getAttributeName()) )
                .findFirst()
                .orElse(null);

        if (singleAttributeValue == null) {
            return false;
        }

        // there must be values from and to in filter
        return Integer.parseInt(filter.getValueFrom()) <= Integer.parseInt(singleAttributeValue.getValue())
                && Integer.parseInt(filter.getValueTo()) >= Integer.parseInt(singleAttributeValue.getValue());

    }

    private boolean filterDouble(List<AttributeValueResponse> productAttributes, SingleFilter filter) {
        // filter productAttributes and use only integer and have single filter name
        AttributeValueResponse  singleAttributeValue = productAttributes
                .stream()
                .filter(x -> x.getType().equals("Double")
                        && x.getAttributeName().equals(filter.getAttributeName()) )
                .findFirst()
                .orElse(null);

        if (singleAttributeValue == null) {
            return false;
        }

        // there must be values from and to in filter
        return Double.parseDouble(filter.getValueFrom()) <= Integer.parseInt(singleAttributeValue.getValue())
                && Double.parseDouble(filter.getValueTo()) >= Integer.parseInt(singleAttributeValue.getValue());
    }

    private boolean filterString(List<AttributeValueResponse> productAttributes, SingleFilter filter) {
        // filter productAttributes and use only string and have single filter name
        AttributeValueResponse  singleAttributeValue = productAttributes
                .stream()
                .filter(x -> x.getType().equals("String")
                        && x.getAttributeName().equals(filter.getAttributeName()))
                .findFirst()
                .orElse(null);

        if (singleAttributeValue == null) {
            return false;
        }

        return singleAttributeValue.getValue().equals(filter.getValueFrom());
    }

    private Page<ProductResponse> convertProductsToPageable(Pageable page, List<ProductResponse> products) {
        int pageSize = page.getPageSize();
        int start = page.getPageNumber() * pageSize;
        int end = Math.min((page.getPageNumber() + 1) * pageSize, products.size());
        return new PageImpl<>(products.subList(start, end), page, products.size());
    }
}

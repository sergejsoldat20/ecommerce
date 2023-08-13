package webshop.services;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import webshop.contracts.ProductServiceContract;
import webshop.models.base.CrudJpaService;
import webshop.models.entities.ProductEntity;
import webshop.models.requests.ProductRequest;
import webshop.models.responses.ProductResponse;
import webshop.repositories.ProductRepository;

import java.util.List;

@Service
public class ProductService extends CrudJpaService<ProductEntity, Integer> implements ProductServiceContract {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;
    public ProductService(ProductRepository productRepository, ModelMapper modelMapper) {
        super(productRepository, modelMapper, ProductEntity.class);
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Page<ProductResponse> getAllProducts(Pageable page) {
        List<ProductResponse> response = productRepository
                .findAll()
                .stream()
                .map(x -> modelMapper.map(x, ProductResponse.class))
                .toList();

        return convertProductsToPageable(page, response);
    }

    @Override
    public Page<ProductRequest> getAllProductsByUserId(Pageable page, Integer accountId) {
        return null;
    }

    @Override
    public Page<ProductRequest> filterProducts(Pageable page, Double priceFrom, Double priceTo, Boolean timeDesc) {
        return null;
    }

    @Override
    public Page<ProductRequest> filterProductsByCategories(Pageable page, Integer categoryId, List<String> filters) {
        return null;
    }

    private Page<ProductResponse> convertProductsToPageable(Pageable page, List<ProductResponse> products) {
        int pageSize = page.getPageSize();
        int start = page.getPageNumber() * pageSize;
        int end = Math.min((page.getPageNumber() + 1) * pageSize, products.size());

        return new PageImpl<>(products.subList(start, end), page, products.size());

    }
}

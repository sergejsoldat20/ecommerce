package webshop.services;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import webshop.contracts.ProductServiceContract;
import webshop.exceptions.AppException;
import webshop.models.base.CrudJpaService;
import webshop.models.entities.ProductEntity;
import webshop.models.requests.ProductRequest;
import webshop.models.responses.ProductResponse;
import webshop.repositories.ProductRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService extends CrudJpaService<ProductEntity, Integer> implements ProductServiceContract {

    private final ProductRepository productRepository;
    private final PhotoService photoService;
    private final ModelMapper modelMapper;
    public ProductService(ProductRepository productRepository, PhotoService photoService, ModelMapper modelMapper) {
        super(productRepository, modelMapper, ProductEntity.class);
        this.productRepository = productRepository;
        this.photoService = photoService;
        this.modelMapper = modelMapper;
    }

    @Override
    public Page<ProductResponse> getAllProducts(Pageable page) {
        List<ProductResponse> response = productRepository
                .findAll()
                .stream()
                .filter(x -> !x.getDeleted())
                .map(x -> modelMapper.map(x, ProductResponse.class))
                .toList();

        return convertProductsToPageable(page, response);
    }

    @Override
    public List<ProductResponse> getAllProductsByUserId(Integer accountId) {
        List<ProductResponse> response = productRepository
                .findAll()
                .stream()
                .filter(x -> x.getAccountId().equals(accountId))
                .filter(x -> !x.getDeleted())
                .map(x -> modelMapper.map(x, ProductResponse.class))
                .toList()
                .stream()
                .map(x -> {

                    if(photoService.getAllPhotosByProductId(x.getId()).size() > 0){
                        x.setPhotoUrl(photoService.getAllPhotosByProductId(x.getId()).get(0).getPhotoUrl());
                    }
                    return x;
                })
                .collect(Collectors.toList());
        return response;
    }

    @Override
    public Page<ProductRequest> filterProducts(Pageable page, Double priceFrom, Double priceTo, Boolean timeDesc) {
        return null;
    }

    @Override
    public Page<ProductRequest> filterProductsByCategories(Pageable page, Integer categoryId, List<String> filters) {
        return null;
    }

    @Override
    public void setToDeleted(Integer id) throws AppException {
        ProductEntity product = super.findById(id, ProductEntity.class);
        product.setDeleted(true);
        productRepository.saveAndFlush(product);
    }

    private Page<ProductResponse> convertProductsToPageable(Pageable page, List<ProductResponse> products) {
        int pageSize = page.getPageSize();
        int start = page.getPageNumber() * pageSize;
        int end = Math.min((page.getPageNumber() + 1) * pageSize, products.size());

        return new PageImpl<>(products.subList(start, end), page, products.size());

    }

}

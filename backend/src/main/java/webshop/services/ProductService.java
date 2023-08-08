package webshop.services;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import webshop.contracts.ProductServiceContract;
import webshop.models.base.CrudJpaService;
import webshop.models.entities.ProductEntity;
import webshop.models.requests.Product;
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
    public Page<Product> getAllProductsByUserId(Pageable page, Integer accountId) {
        return null;
    }

    @Override
    public Page<Product> filterProducts(Pageable page, Double priceFrom, Double priceTo, Boolean timeDesc) {
        return null;
    }

    @Override
    public Page<Product> filterProductsByCategories(Pageable page, Integer categoryId, List<String> filters) {
        return null;
    }
}

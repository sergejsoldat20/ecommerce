package webshop.services;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import webshop.contracts.ProductServiceContract;
import webshop.models.base.CrudJpaService;
import webshop.models.entities.ProductEntity;
import webshop.repositories.ProductRepository;

@Service
public class ProductService extends CrudJpaService<ProductEntity, Integer> implements ProductServiceContract {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;
    public ProductService(ProductRepository productRepository, ModelMapper modelMapper) {
        super(productRepository, modelMapper, ProductEntity.class);
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }
}

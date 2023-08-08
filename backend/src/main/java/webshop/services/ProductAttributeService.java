package webshop.services;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import webshop.contracts.ProductAttributeServiceContract;
import webshop.models.base.CrudJpaService;
import webshop.models.entities.ProductAttributeEntity;
import webshop.models.entities.ProductAttributeEntityPK;
import webshop.models.requests.ProductAttribute;
import webshop.repositories.ProductAttributeRepository;

import java.util.List;

@Service
public class ProductAttributeService extends CrudJpaService<ProductAttributeEntity, ProductAttributeEntityPK> implements ProductAttributeServiceContract {

    private final ProductAttributeRepository repository;
    private final ModelMapper modelMapper;
    public ProductAttributeService(ProductAttributeRepository repository, ModelMapper modelMapper) {
        super(repository, modelMapper, ProductAttributeEntity.class);
        this.repository = repository;
        this.modelMapper = modelMapper;
    }


    @Override
    public void addToDatabase(ProductAttributeEntity entity) {
        repository.saveAndFlush(entity);
    }

    @Override
    public List<ProductAttribute> getAllById(Integer productId, Integer attributeId) {
        return null;
    }
}

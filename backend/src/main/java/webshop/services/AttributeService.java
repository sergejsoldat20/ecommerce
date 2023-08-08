package webshop.services;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import webshop.contracts.AttributeServiceContract;
import webshop.models.base.CrudJpaService;
import webshop.models.entities.AttributeEntity;
import webshop.models.requests.Attribute;
import webshop.repositories.AttributeRepository;

import java.util.List;

@Service
public class AttributeService extends CrudJpaService<AttributeEntity, Integer> implements AttributeServiceContract {

    private final AttributeRepository attributeRepository;
    private final ModelMapper modelMapper;
    public AttributeService(AttributeRepository attributeRepository, ModelMapper modelMapper) {
        super(attributeRepository, modelMapper, AttributeEntity.class);
        this.attributeRepository = attributeRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<Attribute> getAllAttributes() {
        return null;
    }

    @Override
    public List<Attribute> getAllByCategoryId(Integer categoryId) {
        return null;
    }
}

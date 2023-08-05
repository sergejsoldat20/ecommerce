package webshop.services;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import webshop.contracts.AttributeServiceContract;
import webshop.models.base.CrudJpaService;
import webshop.models.entities.AttributeEntity;
import webshop.repositories.AttributeRepository;

@Service
public class AttributeService extends CrudJpaService<AttributeEntity, Integer> implements AttributeServiceContract {

    private final AttributeRepository attributeRepository;
    private final ModelMapper modelMapper;
    public AttributeService(AttributeRepository attributeRepository, ModelMapper modelMapper) {
        super(attributeRepository, modelMapper, AttributeEntity.class);
        this.attributeRepository = attributeRepository;
        this.modelMapper = modelMapper;
    }
}

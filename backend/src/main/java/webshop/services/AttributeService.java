package webshop.services;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import webshop.contracts.AttributeServiceContract;
import webshop.models.base.CrudJpaService;
import webshop.models.entities.AttributeEntity;
import webshop.models.requests.Attribute;
import webshop.models.responses.AttributeResponse;
import webshop.repositories.AttributeRepository;

import java.util.List;
import java.util.stream.Collectors;

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
    public List<AttributeResponse> getAllByCategoryId(Integer categoryId) {
        return attributeRepository.getAllByCategoryId(categoryId)
                .stream()
                .map(x -> modelMapper.map(x, AttributeResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public String getAttributeTypeByName(String name) {
        return attributeRepository
                .findAll()
                .stream()
                .filter(x -> x.getName().equals(name))
                .map(AttributeEntity::getType)
                .findFirst()
                .orElse(null);
    }


}

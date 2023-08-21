package webshop.services;

import org.modelmapper.ModelMapper;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import webshop.contracts.ProductAttributeServiceContract;
import webshop.models.base.CrudJpaService;
import webshop.models.entities.ProductAttributeEntity;
import webshop.models.entities.ProductAttributeEntityPK;
import webshop.models.entities.ProductEntity;
import webshop.models.requests.ProductAttributeRequest;
import webshop.models.responses.AttributeResponse;
import webshop.models.responses.AttributeValueResponse;
import webshop.repositories.AttributeRepository;
import webshop.repositories.ProductAttributeRepository;
import webshop.repositories.ProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductAttributeService extends CrudJpaService<ProductAttributeEntity, ProductAttributeEntityPK> implements ProductAttributeServiceContract {

    private final ProductAttributeRepository repository;
    private final AttributeRepository attributeRepository;
    private final ProductRepository productRepository;

    private final ModelMapper modelMapper;
    public ProductAttributeService(ProductAttributeRepository repository,
                                   AttributeService attributeService,
                                   AttributeRepository attributeRepository,
                                   ProductRepository productRepository,
                                   ModelMapper modelMapper) {
        super(repository, modelMapper, ProductAttributeEntity.class);
        this.repository = repository;
        this.attributeRepository = attributeRepository;
        this.productRepository = productRepository;

        this.modelMapper = modelMapper;
    }

    @Override
    public void addToDatabase(ProductAttributeEntity entity) {
        repository.saveAndFlush(entity);
    }

    @Override
    public List<ProductAttributeRequest> getAllById(Integer productId, Integer attributeId) {
        return null;
    }

    @Override
    public List<AttributeValueResponse> getAttributeValuesByProductId(Integer productId) {
        List<AttributeValueResponse> response = new ArrayList<>();

        ProductEntity product = productRepository.getReferenceById(productId);

        List<AttributeResponse> attributesList = attributeRepository
                .getAllByCategoryId(product.getCategoryId())
                .stream()
                .map(x -> modelMapper.map(x, AttributeResponse.class))
                .collect(Collectors.toList());


        List<ProductAttributeEntity> productAttributesByProductId = repository
                .findAll()
                .stream()
                .filter(x -> x.getId().getProductId() == productId)
                .collect(Collectors.toList());

        // productAttributesByProductId.stream().forEach(System.out::println);

        for(ProductAttributeEntity item : productAttributesByProductId) {
            AttributeValueResponse responseItem = new AttributeValueResponse();
            responseItem.setValue(item.getValue());
            AttributeResponse currentAttribute = attributesList
                    .stream()
                    .filter(x -> x.getId() == item.getId().getAttributeId())
                    .findFirst()
                    .orElse(null);


            responseItem.setAttributeName(currentAttribute.getName());
            responseItem.setType(currentAttribute.getType());

            response.add(responseItem);
        }
        return response;
    }


}

package webshop.contracts;

import webshop.models.base.CrudService;
import webshop.models.entities.ProductAttributeEntity;
import webshop.models.entities.ProductAttributeEntityPK;
import webshop.models.requests.ProductAttributeRequest;
import webshop.models.responses.AttributeValueResponse;

import java.util.List;

public interface ProductAttributeServiceContract extends CrudService<ProductAttributeEntityPK> {

    void addToDatabase(ProductAttributeEntity entity);
    List<ProductAttributeRequest> getAllById(Integer productId, Integer attributeId);

    List<AttributeValueResponse> getAttributeValuesByProductId(Integer productId);
}

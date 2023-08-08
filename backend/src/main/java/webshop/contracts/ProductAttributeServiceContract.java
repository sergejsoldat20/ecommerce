package webshop.contracts;

import webshop.models.base.CrudService;
import webshop.models.entities.ProductAttributeEntity;
import webshop.models.entities.ProductAttributeEntityPK;
import webshop.models.requests.ProductAttribute;

import java.util.List;

public interface ProductAttributeServiceContract extends CrudService<ProductAttributeEntityPK> {

    void addToDatabase(ProductAttributeEntity entity);
    List<ProductAttribute> getAllById(Integer productId, Integer attributeId);
}

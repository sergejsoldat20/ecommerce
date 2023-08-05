package webshop.contracts;

import webshop.models.base.CrudService;
import webshop.models.entities.ProductAttributeEntity;
import webshop.models.entities.ProductAttributeEntityPK;

public interface ProductAttributeServiceContract extends CrudService<ProductAttributeEntityPK> {

    void addToDatabase(ProductAttributeEntity entity);
}

package webshop.contracts;

import webshop.models.base.CrudService;
import webshop.models.requests.Attribute;

import java.util.List;

public interface AttributeServiceContract extends CrudService<Integer> {

    List<Attribute> getAllAttributes();
    List<Attribute> getAllByCategoryId(Integer categoryId);
}

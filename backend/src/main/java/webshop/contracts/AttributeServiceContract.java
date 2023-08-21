package webshop.contracts;

import webshop.models.base.CrudService;
import webshop.models.requests.Attribute;
import webshop.models.responses.AttributeResponse;

import java.util.List;

public interface AttributeServiceContract extends CrudService<Integer> {

    List<Attribute> getAllAttributes();
    List<AttributeResponse> getAllByCategoryId(Integer categoryId);

    String getAttributeTypeByName(String name);
}

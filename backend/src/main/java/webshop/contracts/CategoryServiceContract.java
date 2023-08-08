package webshop.contracts;

import webshop.models.base.CrudService;
import webshop.models.requests.Category;

import java.util.List;

public interface CategoryServiceContract extends CrudService<Integer> {
    List<Category> getAllCategories();
}

package webshop.contracts;

import webshop.models.base.CrudService;
import webshop.models.responses.Category;

import java.util.List;

public interface CategoryServiceContract extends CrudService<Integer> {
    List<Category> getAllCategories();
    Category findCategoryById(Integer id);
    Category findCategoryByName(String name);
}

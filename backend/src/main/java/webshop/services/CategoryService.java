package webshop.services;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import webshop.contracts.CategoryServiceContract;
import webshop.models.base.BaseEntity;
import webshop.models.base.CrudJpaService;
import webshop.models.entities.CategoryEntity;
import webshop.models.responses.Category;
import webshop.repositories.CategoryRepository;

import java.io.Serializable;
import java.util.List;

@Service
public class CategoryService extends CrudJpaService<CategoryEntity, Integer> implements CategoryServiceContract {

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;
    public CategoryService(CategoryRepository categoryRepository, ModelMapper modelMapper) {
        super(categoryRepository, modelMapper, CategoryEntity.class);
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<Category> getAllCategories() {
        return null;
    }

    @Override
    public Category findCategoryById(Integer id) {
        return modelMapper.map(categoryRepository.findById(id), Category.class);

    }

    @Override
    public Category findCategoryByName(String name) {
        return null;
    }


}

package webshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import webshop.models.entities.CategoryEntity;
import webshop.services.CategoryService;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Integer> {

    CategoryEntity findByName(String name);
}

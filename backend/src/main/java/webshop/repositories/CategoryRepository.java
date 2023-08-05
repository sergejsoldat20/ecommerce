package webshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import webshop.models.entities.CategoryEntity;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Integer> {
}

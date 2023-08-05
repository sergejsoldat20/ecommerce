package webshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import webshop.models.entities.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {

}

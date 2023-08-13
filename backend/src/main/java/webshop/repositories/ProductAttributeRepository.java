package webshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import webshop.models.entities.ProductAttributeEntity;
import webshop.models.entities.ProductAttributeEntityPK;

import java.util.List;

public interface ProductAttributeRepository extends JpaRepository<ProductAttributeEntity, ProductAttributeEntityPK> {

    List<ProductAttributeEntity> getAllById(ProductAttributeEntityPK id);
}

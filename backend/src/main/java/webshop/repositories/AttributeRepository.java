package webshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import webshop.models.entities.AttributeEntity;

import java.util.List;

public interface AttributeRepository extends JpaRepository<AttributeEntity, Integer> {

    List<AttributeEntity> getAllByCategoryId(Integer categoryId);

}

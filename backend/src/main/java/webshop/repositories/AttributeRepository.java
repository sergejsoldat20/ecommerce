package webshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import webshop.models.entities.AttributeEntity;

public interface AttributeRepository extends JpaRepository<AttributeEntity, Integer> {
}

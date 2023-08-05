package webshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import webshop.models.entities.PhotoEntity;

public interface PhotoRepository extends JpaRepository<PhotoEntity, Integer> {
}

package webshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import webshop.models.entities.PhotoEntity;

import java.util.List;

public interface PhotoRepository extends JpaRepository<PhotoEntity, Integer> {

    List<PhotoEntity> getPhotoEntitiesByProductId(Integer productId);
}

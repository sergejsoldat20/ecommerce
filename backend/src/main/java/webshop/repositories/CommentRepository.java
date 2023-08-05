package webshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import webshop.models.entities.CommentEntity;

public interface CommentRepository extends JpaRepository<CommentEntity, Integer> {
}

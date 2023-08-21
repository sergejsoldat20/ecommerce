package webshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import webshop.models.entities.ReportMessageEntity;

import java.util.List;

public interface ReportMessageRepository extends JpaRepository<ReportMessageEntity, Integer> {

    List<ReportMessageEntity> getByAccountId(Integer id);
}

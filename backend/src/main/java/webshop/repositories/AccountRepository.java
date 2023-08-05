package webshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import webshop.models.entities.AccountEntity;

public interface AccountRepository extends JpaRepository<AccountEntity, Integer> {
}

package webshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import webshop.models.entities.AccountEntity;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<AccountEntity, Integer> {

    AccountEntity findByEmail(String email);
    Optional<AccountEntity> findByUsername(String username);

    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
    AccountEntity getByUsername(String username);

    // AccountEntity findById(Integer id);

}

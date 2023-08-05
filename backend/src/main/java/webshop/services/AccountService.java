package webshop.services;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import webshop.contracts.AccountServiceContract;
import webshop.models.base.CrudJpaService;
import webshop.models.entities.AccountEntity;
import webshop.repositories.AccountRepository;

@Service
public class AccountService extends CrudJpaService<AccountEntity, Integer> implements AccountServiceContract {

    private final AccountRepository accountRepository;
    private final ModelMapper modelMapper;
    public AccountService(AccountRepository accountRepository, ModelMapper modelMapper) {
        super(accountRepository, modelMapper, AccountEntity.class);
        this.accountRepository = accountRepository;
        this.modelMapper = modelMapper;
    }
}

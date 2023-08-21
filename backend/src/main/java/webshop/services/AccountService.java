package webshop.services;

import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import webshop.contracts.AccountServiceContract;
import webshop.exceptions.AppException;
import webshop.models.base.CrudJpaService;
import webshop.models.entities.AccountEntity;
import webshop.models.requests.Account;
import webshop.models.responses.AccountResponse;
import webshop.repositories.AccountRepository;

import java.util.List;

@Service
public class AccountService extends CrudJpaService<AccountEntity, Integer> implements AccountServiceContract {

    private final AccountRepository accountRepository;
    private final ModelMapper modelMapper;
    public AccountService(AccountRepository accountRepository, ModelMapper modelMapper) {
        super(accountRepository, modelMapper, AccountEntity.class);
        this.accountRepository = accountRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Boolean checkIfEmailExists(String email) {
        return null;
    }

    @Override
    public List<AccountResponse> getAllAccounts() {
        return accountRepository
                .findAll()
                .stream()
                .map(e -> modelMapper.map(e, AccountResponse.class))
                .toList();
    }

    @Override
    public AccountResponse getCurrentAccount() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return super.getModelMapper().map(accountRepository.findByUsername(username), AccountResponse.class);
    }

    @Override
    public Integer getCurrentAccountId() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return super.getModelMapper().map(accountRepository.findByUsername(username), AccountResponse.class).getId();
    }

    @Override
    public AccountResponse getAccountById(Integer id) throws AppException {
        return super.findById(id, AccountResponse.class);
    }

    @Override
    public Boolean getAccountConfirmed(String email) {
        return null;
    }

    @Override
    public AccountEntity getAccountByUsername(String username) {
        return accountRepository.getByUsername(username);
    }


}

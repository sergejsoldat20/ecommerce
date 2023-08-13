package webshop.contracts;

import webshop.models.base.CrudService;
import webshop.models.requests.Account;
import webshop.models.responses.AccountResponse;

import java.util.List;

public interface AccountServiceContract extends CrudService<Integer> {
    Boolean checkIfEmailExists(String email);
    List<AccountResponse> getAllAccounts();
    AccountResponse getCurrentAccount();
    Integer getCurrentAccountId();
    AccountResponse getAccountById(Integer id);
    Boolean getAccountConfirmed(String email);
}

package webshop.contracts;

import webshop.exceptions.AppException;
import webshop.models.base.CrudService;
import webshop.models.requests.Account;
import webshop.models.responses.AccountResponse;

import java.util.List;

public interface AccountServiceContract extends CrudService<Integer> {
    Boolean checkIfEmailExists(String email);
    List<AccountResponse> getAllAccounts();
    AccountResponse getCurrentAccount();
    Integer getCurrentAccountId();
    AccountResponse getAccountById(Integer id) throws AppException;
    Boolean getAccountConfirmed(String email);
}

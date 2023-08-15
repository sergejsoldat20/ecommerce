package webshop.controllers;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import webshop.exceptions.AppException;
import webshop.models.responses.AccountResponse;
import webshop.services.AccountService;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("account")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("current")
    public ResponseEntity<AccountResponse> getCurrentAccount() {
        return ResponseEntity.ok(accountService.getCurrentAccount());
    }

    @GetMapping("/by-id/{id}")
    public ResponseEntity<AccountResponse> getAccountById(@PathVariable Integer id) throws AppException {
        AccountResponse response = accountService.getAccountById(id);
        if(response != null) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}


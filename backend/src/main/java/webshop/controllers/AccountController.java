package webshop.controllers;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import webshop.models.responses.AccountResponse;
import webshop.services.AccountService;

import java.util.List;

@RestController
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
}


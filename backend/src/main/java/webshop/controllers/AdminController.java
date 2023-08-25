package webshop.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import webshop.exceptions.AppException;
import webshop.models.entities.AccountEntity;
import webshop.models.entities.CategoryEntity;
import webshop.models.requests.Account;
import webshop.models.requests.AccountRequest;
import webshop.models.responses.AccountResponse;
import webshop.models.responses.Category;
import webshop.security.SecurityConsts;
import webshop.services.AccountService;
import webshop.services.CategoryService;
import java.util.List;

@RestController
@RequestMapping("/admin-app")
public class AdminController {

    private final AccountService accountService;
    private final CategoryService categoryService;

    public AdminController(AccountService accountService, CategoryService categoryService) {
        this.accountService = accountService;
        this.categoryService = categoryService;
    }

    @GetMapping("/categories")
    public List<CategoryEntity> getAllCategories() {
        return categoryService
                .findAll(CategoryEntity.class)
                .stream()
                .filter(x -> !x.getIsDeleted())
                .toList();
    }

    @GetMapping("/categories/{id}")
    public CategoryEntity getCategoryById(@PathVariable Integer id) throws AppException {
        return categoryService.findById(id, CategoryEntity.class);
    }

    @PostMapping("/update-category")
    public ResponseEntity<?> updateCategory(@RequestBody Category request) throws AppException {
        CategoryEntity category = categoryService.findById(request.getId(), CategoryEntity.class);
        category.setName(request.getName());
        categoryService.update(request.getId(), category, CategoryEntity.class);
        return ResponseEntity.ok("Category is updated!");
    }

    @DeleteMapping("/delete-category/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Integer id) throws AppException {
        CategoryEntity category = categoryService.findById(id, CategoryEntity.class);
        //update category and set isDeleted to true
        category.setIsDeleted(true);
        categoryService.update(id, category, CategoryEntity.class);
        return ResponseEntity.ok("Category is deleted!");
    }

    @PostMapping("/insert-category")
    public ResponseEntity<?> insertCategory(@RequestBody Category category) {
        category.setIsDeleted(false);
        categoryService.insert(category, CategoryEntity.class);
        return ResponseEntity.ok("Category is inserted!");
    }

    @GetMapping("/accounts")
    public List<AccountResponse> getAllAccounts(){
        return accountService
                .getAllAccounts();
    }

    @GetMapping("/accounts/{id}")
    public AccountResponse getAccountById(@PathVariable Integer id) throws AppException {
        return accountService.findById(id, AccountResponse.class);
    }

    @PostMapping("/update-account/{id}")
    public ResponseEntity<?> updateAccount(@RequestBody AccountRequest request, @PathVariable Integer id) throws AppException {
        AccountEntity account = accountService.findById(id, AccountEntity.class);

        account.setFirstName(request.getFirstName());
        account.setLastName(request.getLastName());
        account.setUsername(request.getUsername());
        account.setLocation(request.getLocation());
        account.setPhoneNumber(request.getPhoneNumber());

        accountService.update(request.getId(), account, AccountEntity.class);
        return ResponseEntity.ok("Account is updated!");
    }

    @DeleteMapping("/delete-account/{id}")
    public ResponseEntity<?> deleteAccount(@PathVariable Integer id) throws AppException {
        AccountEntity account = accountService.findById(id, AccountEntity.class);
        //update account and set is confirmed
        account.setAccountConfirmed(false);
        accountService.update(id, account, AccountEntity.class);
        return ResponseEntity.ok("Account is disabled!");
    }

    @PostMapping("/insert-account")
    public ResponseEntity<?> insertAccount(@RequestBody Account request) {
        request.setIsAccountConfirmed(true);
        request.setRole(SecurityConsts.ADMIN);
        accountService.insert(request, AccountResponse.class);
        return ResponseEntity.ok("Account inserted!");
    }
}

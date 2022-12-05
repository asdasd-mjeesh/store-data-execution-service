package data_execution.data_execution.rest_controller;

import data_execution.data_execution.entity.account.Account;
import data_execution.data_execution.service.account.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/accounts")
public class AccountControllerV1 {
    private final AccountService accountService;

    public AccountControllerV1(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/")
    public ResponseEntity<?> createAccount(@RequestBody Account account) {
        var savedAccount = accountService.save(account);
        return ResponseEntity.ok(savedAccount);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable(name = "id") Long id) {
        var account = accountService.getById(id);
        if (account.isPresent()) {
            return ResponseEntity.ok(account.get());
        }
        return new ResponseEntity<>(String.format("Account with id=%s not found", id),
                HttpStatus.NOT_FOUND);
    }


}

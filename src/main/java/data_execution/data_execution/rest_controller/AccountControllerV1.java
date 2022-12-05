package data_execution.data_execution.rest_controller;

import data_execution.data_execution.dto.request.account.AccountSaveDto;
import data_execution.data_execution.entity.account.Account;
import data_execution.data_execution.service.account.AccountService;
import data_execution.data_execution.service.mapper.AccountMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/accounts")
public class AccountControllerV1 {
    private final AccountService accountService;
    private final AccountMapper accountMapper;

    public AccountControllerV1(AccountService accountService, AccountMapper accountMapper) {
        this.accountService = accountService;
        this.accountMapper = accountMapper;
    }

    @PostMapping("/")
    public ResponseEntity<?> createAccount(@RequestBody AccountSaveDto account) {
        var savedAccount = accountService.save(account);
        var accountDto = accountMapper.map(savedAccount);
        return ResponseEntity.ok(accountDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable(name = "id") Long id) {
        var account = accountService.getById(id);
        if (account.isPresent()) {
            var accountDto = accountMapper.map(account.get());
            return ResponseEntity.ok(accountDto);
        }
        return new ResponseEntity<>(String.format("Account with id=%s not found", id),
                HttpStatus.NOT_FOUND);
    }

    @PutMapping("/")
    public ResponseEntity<String> update(@RequestBody Account account) {
        boolean isUpdated = accountService.update(account);
        if (isUpdated) {
            return ResponseEntity.ok("Updated successfully");
        }
        return new ResponseEntity<>("Update was failed", HttpStatus.CONFLICT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable(name = "id") Long id) {
        boolean isDeleted = accountService.deleteById(id);
        if (isDeleted) {
            return ResponseEntity.ok("Deleted successfully");
        }
        return new ResponseEntity<>("Delete was failed", HttpStatus.CONFLICT);
    }
}

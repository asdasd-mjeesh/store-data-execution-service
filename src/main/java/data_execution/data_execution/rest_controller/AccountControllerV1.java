package data_execution.data_execution.rest_controller;

import data_execution.data_execution.dto.request.account.AccountSaveDto;
import data_execution.data_execution.dto.response.AccountDto;
import data_execution.data_execution.entity.account.Account;
import data_execution.data_execution.entity.account.PermissionEnum;
import data_execution.data_execution.entity.account.RoleName;
import data_execution.data_execution.service.account.AccountRolePermissionService;
import data_execution.data_execution.service.account.AccountService;
import data_execution.data_execution.service.mapper.AccountMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/accounts")
public class AccountControllerV1 {
    private final AccountService accountService;
    private final AccountRolePermissionService accountRolePermissionService;
    private final AccountMapper accountMapper;

    public AccountControllerV1(AccountService accountService,
                               AccountRolePermissionService accountRolePermissionService,
                               AccountMapper accountMapper) {
        this.accountService = accountService;
        this.accountRolePermissionService = accountRolePermissionService;
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
        boolean isUpdated = accountService.updateWithConfirmation(account);
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

    @PostMapping("/changeRole")
    public ResponseEntity<?> changeRole(@RequestParam(name = "accountId", defaultValue = "null") Long accountId,
                                        @RequestParam(name = "roleName", defaultValue = "null") RoleName roleName) {
        if (accountId == null || roleName == null) {
            return new ResponseEntity<>(
                    String.format("Incorrect parameters: accountId=%s, roleName=%s", accountId, roleName),
                    HttpStatus.BAD_REQUEST);
        }
        Account changedAccount = accountRolePermissionService.changeRole(accountId, roleName);
        AccountDto accountDto = accountMapper.map(changedAccount);
        return ResponseEntity.ok(accountDto);
    }

    @PostMapping("/addPermissions")
    public ResponseEntity<?> addPermissions(@RequestParam(name = "accountId", defaultValue = "null") Long accountId,
                                            @RequestBody(required = false) List<PermissionEnum> permissionEnums) {
        if (accountId == null || permissionEnums == null) {
            return new ResponseEntity<>(
                    String.format("Incorrect parameters: accountId=%s, permissions=%s", accountId, permissionEnums),
                    HttpStatus.BAD_REQUEST);
        }
        Account changedAccount = accountRolePermissionService.addPermissionsToAccount(accountId, permissionEnums);
        AccountDto accountDto = accountMapper.map(changedAccount);
        return ResponseEntity.ok(accountDto);
    }
}

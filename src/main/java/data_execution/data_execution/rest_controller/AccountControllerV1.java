package data_execution.data_execution.rest_controller;

import data_execution.data_execution.dto.request.account.AccountRequest;
import data_execution.data_execution.dto.response.account.AccountResponse;
import data_execution.data_execution.entity.account.Account;
import data_execution.data_execution.entity.account.PermissionEnum;
import data_execution.data_execution.entity.account.RoleName;
import data_execution.data_execution.service.account.AccountRolePermissionService;
import data_execution.data_execution.service.account.AccountService;
import data_execution.data_execution.service.mapper.response.account.AccountResponseMapper;
import data_execution.data_execution.service.mapper.request.account.AccountRequestMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/accounts")
public class AccountControllerV1 {
    private final AccountService accountService;
    private final AccountRolePermissionService accountRolePermissionService;
    private final AccountResponseMapper accountResponseMapper;
    private final AccountRequestMapper accountRequestMapper;

    public AccountControllerV1(AccountService accountService,
                               AccountRolePermissionService accountRolePermissionService,
                               AccountResponseMapper accountResponseMapper,
                               AccountRequestMapper accountRequestMapper) {
        this.accountService = accountService;
        this.accountRolePermissionService = accountRolePermissionService;
        this.accountResponseMapper = accountResponseMapper;
        this.accountRequestMapper = accountRequestMapper;
    }

    @PostMapping("/addPermissions")
    public ResponseEntity<?> addPermissions(@RequestParam(name = "accountId") Long accountId,
                                            @RequestBody List<PermissionEnum> permissionEnums) {
        Account changedAccount = accountRolePermissionService.addPermissionsToAccount(accountId, permissionEnums);
        AccountResponse accountResponse = accountResponseMapper.map(changedAccount);
        return ResponseEntity.ok(accountResponse);
    }

    @PostMapping("/deletePermissions")
    public ResponseEntity<?> deletePermissions(@RequestParam(name = "accountId") Long accountId,
                                               @RequestBody List<PermissionEnum> permissionEnums) {
        Account changedAccount = accountRolePermissionService.deletePermissions(accountId, permissionEnums);
        AccountResponse accountResponse = accountResponseMapper.map(changedAccount);
        return ResponseEntity.ok(accountResponse);
    }

    @PostMapping("/")
    public ResponseEntity<?> createAccount(@RequestBody AccountRequest account) {
        var accountForSaving = accountRequestMapper.map(account);
        accountForSaving = accountService.create(accountForSaving);
        var accountDto = accountResponseMapper.map(accountForSaving);
        return ResponseEntity.ok(accountDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable(name = "id") Long id) {
        var account = accountService.getById(id);
        if (account.isPresent()) {
            var accountDto = accountResponseMapper.map(account.get());
            return ResponseEntity.ok(accountDto);
        }
        return new ResponseEntity<>(String.format("Account with id=%s not found", id), HttpStatus.NOT_FOUND);
    }

    @PatchMapping("/")
    public ResponseEntity<String> update(@RequestBody AccountRequest accountRequest) {
        var account = accountRequestMapper.map(accountRequest);
        boolean isUpdated = accountService.updateWithConfirmation(account);
        if (isUpdated) {
            return ResponseEntity.ok("Updated successfully");
        }
        return new ResponseEntity<>("Update was failed", HttpStatus.CONFLICT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable(name = "id") Long id) {
        boolean isDeleted = accountService.deleteById(id);
        if (isDeleted) {
            return ResponseEntity.ok("Deleted successfully");
        }
        return new ResponseEntity<>("Delete was failed", HttpStatus.CONFLICT);
    }

    @PostMapping("/changeRole")
    public ResponseEntity<?> changeRole(@RequestParam(name = "accountId") Long accountId,
                                        @RequestParam(name = "roleName") RoleName roleName) {
        Account changedAccount = accountRolePermissionService.changeRole(accountId, roleName);
        AccountResponse accountResponse = accountResponseMapper.map(changedAccount);
        return ResponseEntity.ok(accountResponse);
    }
}

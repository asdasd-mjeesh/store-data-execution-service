package data_execution.data_execution.rest_controller;

import data_execution.data_execution.dto.filter.AccountFilter;
import data_execution.data_execution.dto.request.account.AccountRequest;
import data_execution.data_execution.dto.response.account.AccountResponse;
import data_execution.data_execution.entity.account.Account;
import data_execution.data_execution.entity.account.AccountStatus;
import data_execution.data_execution.entity.account.PermissionEnum;
import data_execution.data_execution.entity.account.RoleName;
import data_execution.data_execution.service.account.AccountConfirmationService;
import data_execution.data_execution.service.account.AccountRolePermissionService;
import data_execution.data_execution.service.account.AccountService;
import data_execution.data_execution.service.mapper.response.account.AccountResponseMapper;
import data_execution.data_execution.service.mapper.request.account.AccountRequestMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/accounts")
@Slf4j
public class AccountControllerV1 {
    private final AccountService accountService;
    private final AccountConfirmationService accountConfirmationService;
    private final AccountRolePermissionService accountRolePermissionService;
    private final AccountResponseMapper accountResponseMapper;
    private final AccountRequestMapper accountRequestMapper;

    public AccountControllerV1(AccountService accountService,
                               AccountConfirmationService accountConfirmationService,
                               AccountRolePermissionService accountRolePermissionService,
                               AccountResponseMapper accountResponseMapper,
                               AccountRequestMapper accountRequestMapper) {
        this.accountService = accountService;
        this.accountConfirmationService = accountConfirmationService;
        this.accountRolePermissionService = accountRolePermissionService;
        this.accountResponseMapper = accountResponseMapper;
        this.accountRequestMapper = accountRequestMapper;
    }

    @PatchMapping("/confirm")
    public ResponseEntity<String> confirmAccount(@RequestParam("accountId") Long accountId) {
        boolean isConfirmed = accountConfirmationService.confirm(accountId);
        if (isConfirmed) {
            return ResponseEntity.ok("Confirmed");
        }
        return new ResponseEntity<>("Confirmation failed", HttpStatus.CONFLICT);
    }

    @PatchMapping("/addPermissions")
    public ResponseEntity<AccountResponse> addPermissions(@RequestParam(name = "accountId") Long accountId,
                                                          @RequestBody List<PermissionEnum> permissionEnums) {
        Account changedAccount = accountRolePermissionService.addPermissionsToAccount(accountId, permissionEnums);
        AccountResponse accountResponse = accountResponseMapper.map(changedAccount);
        return ResponseEntity.ok(accountResponse);
    }

    @DeleteMapping("/deletePermissions")
    public ResponseEntity<AccountResponse> deletePermissions(@RequestParam(name = "accountId") Long accountId,
                                                             @RequestBody List<PermissionEnum> permissionEnums) {
        var changedAccount = accountRolePermissionService.deletePermissions(accountId, permissionEnums);
        var accountResponse = accountResponseMapper.map(changedAccount);
        return ResponseEntity.ok(accountResponse);
    }

    @PatchMapping("/changeRole")
    public ResponseEntity<AccountResponse> changeRole(@RequestParam(name = "accountId") Long accountId,
                                                      @RequestParam(name = "roleName") RoleName roleName) {
        Account changedAccount = accountRolePermissionService.changeRole(accountId, roleName);
        AccountResponse accountResponse = accountResponseMapper.map(changedAccount);
        return ResponseEntity.ok(accountResponse);
    }

    @PostMapping("/")
    public ResponseEntity<AccountResponse> createAccount(@RequestBody AccountRequest accountRequest) {
        if (accountRequest.getStatus() == null &&
            accountRequest.getStatus() != AccountStatus.NOT_CONFIRMED) {
            log.warn(String.format("Account has been come with status=%s, which was changed to %s",
                    accountRequest.getStatus(), AccountStatus.NOT_CONFIRMED));
            accountRequest.setStatus(AccountStatus.NOT_CONFIRMED);
        }
        var account = accountRequestMapper.map(accountRequest);
        account = accountService.create(account);
        var accountResponse = accountResponseMapper.map(account);
        return new ResponseEntity<>(accountResponse, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountResponse> getById(@PathVariable(name = "id") Long id) {
        var account = accountService.getAccountByIdWithResultChecking(id);
        var accountResponse = accountResponseMapper.map(account);
        return ResponseEntity.ok(accountResponse);
    }

    @PutMapping("/filter")
    public ResponseEntity<List<AccountResponse>> getByFilter(@RequestBody AccountFilter filter) {
        var accounts = accountService.getByFilter(filter);
        var accountsResponse = accountResponseMapper.map(accounts);
        return ResponseEntity.ok(accountsResponse);
    }

    @GetMapping("/")
    public ResponseEntity<AccountResponse> getByEmail(@RequestParam(name = "email") String email) {
        var account = accountService.getByEmail(email);
        var accountResponse = accountResponseMapper.map(account);
        return ResponseEntity.ok(accountResponse);
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
}

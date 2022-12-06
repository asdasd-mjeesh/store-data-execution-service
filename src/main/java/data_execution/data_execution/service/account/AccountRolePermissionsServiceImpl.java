package data_execution.data_execution.service.account;

import data_execution.data_execution.entity.account.*;
import data_execution.data_execution.exception.EntityNotFoundException;
import data_execution.data_execution.service.factory.roles.RolesFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
public class AccountRolePermissionsServiceImpl implements AccountRolePermissionService {
    private static final String ENTITY_NOT_FOUND_ERROR_MSG_TEMPLATE = "%s with %s=%s not found";
    private final AccountService accountService;
    private final PermissionService permissionService;
    private final RoleService roleService;
    private final RolesFactory rolesFactory;

    public AccountRolePermissionsServiceImpl(AccountService accountService,
                                             PermissionService permissionService,
                                             RoleService roleService, RolesFactory rolesFactory) {
        this.accountService = accountService;
        this.permissionService = permissionService;
        this.roleService = roleService;
        this.rolesFactory = rolesFactory;
    }

    private Account getAccount(Long accountId) {
        Optional<Account> account = accountService.getById(accountId);
        if (account.isEmpty()) {
            String errorMsg = String.format(
                    ENTITY_NOT_FOUND_ERROR_MSG_TEMPLATE, Account.class.getSimpleName(), "id", accountId);
            log.error(errorMsg);
            throw new EntityNotFoundException(errorMsg);
        }
        return account.get();
    }

    @Override
    public Account changeRole(Long accountId, RoleName roleName) {
        Account account = this.getAccount(accountId);
        Long oldRoleId = account.getRole().getId();

        Role role = rolesFactory.getRoleByRoleName(roleName);
        account.setRole(role);
        account = accountService.update(account);

        roleService.deleteById(oldRoleId);
        return account;
    }

    @Override
    public Account addPermissionsToAccount(Long accountId, List<PermissionEnum> permissionEnums) {
        Account account = this.getAccount(accountId);
        Set<Permission> permissions = permissionEnums.stream()
                .map(permissionEnum -> permissionService.getByPermissionName(permissionEnum)
                        .orElseThrow(() -> new EntityNotFoundException(String.format(
                                        ENTITY_NOT_FOUND_ERROR_MSG_TEMPLATE, Permission.class, "name", permissionEnum))))
                .collect(Collectors.toSet());

        account.getRole().getPermissions().addAll(permissions);
        return accountService.update(account);
    }

    @Override
    public Account deletePermissions(Long accountId, List<PermissionEnum> permissions) {
        return null;
    }
}

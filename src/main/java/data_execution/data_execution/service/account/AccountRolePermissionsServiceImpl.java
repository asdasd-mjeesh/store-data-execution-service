package data_execution.data_execution.service.account;

import data_execution.data_execution.entity.account.Account;
import data_execution.data_execution.entity.account.Permission;
import data_execution.data_execution.entity.account.Role;
import data_execution.data_execution.entity.account.RoleName;
import data_execution.data_execution.exception.EntityNotFoundException;
import data_execution.data_execution.service.factory.roles.RolesFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class AccountRolePermissionsServiceImpl implements AccountRolePermissionService {
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

    @Override
    public Account changeRole(Long accountId, RoleName roleName) {
        var account = accountService.getById(accountId);
        if (account.isEmpty()) {
            String errorMsg = String.format("Account with id=%s not found", accountId);
            log.error(errorMsg);
            throw new EntityNotFoundException(errorMsg);
        }

        Long oldRoleId = account.get().getRole().getId();

        Account accountWithNewRole = account.get();
        Role role = rolesFactory.getRoleByRoleName(roleName);
        accountWithNewRole.setRole(role);
        accountWithNewRole = accountService.update(accountWithNewRole);

        roleService.deleteById(oldRoleId);
        return accountWithNewRole;
    }

    @Override
    public boolean addPermissionsToAccount(List<Permission> permission, Long accountId) {
        return false;
    }

    @Override
    public boolean deletePermissions(List<Permission> permission, Long accountId) {
        return false;
    }
}

package data_execution.data_execution.service.account;

import data_execution.data_execution.entity.account.Permission;
import data_execution.data_execution.exception.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class AccountRolePermissionsServiceImpl implements AccountRolePermissionService {
    private final AccountService accountService;
    private final PermissionService permissionService;
    private final RoleService roleService;

    public AccountRolePermissionsServiceImpl(AccountService accountService,
                                             PermissionService permissionService,
                                             RoleService roleService) {
        this.accountService = accountService;
        this.permissionService = permissionService;
        this.roleService = roleService;
    }

    @Override
    public boolean changeRole(Long roleId, Long accountId) {
        var account = accountService.getById(accountId);
        if (account.isEmpty()) {
            String errorMsg = String.format("Role with id=%s not found", roleId);
            log.error(errorMsg);
            throw new EntityNotFoundException(errorMsg);
        }
        var role = roleService.getById(roleId);
        if (role.isEmpty()) {
            String errorMsg = String.format("Role with id=%s not found", roleId);
            log.error(errorMsg);
            throw new EntityNotFoundException(errorMsg);
        }

        var accountWithNewRole = account.get();
        accountWithNewRole.setRole(role.get());
        return accountService.update(accountWithNewRole);
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

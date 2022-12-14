package data_execution.data_execution.service.account;

import data_execution.data_execution.entity.account.*;
import data_execution.data_execution.exception.EntityNotFoundException;
import data_execution.data_execution.service.factory.roles.RolesFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AccountRolePermissionsServiceImpl implements AccountRolePermissionService {
    private final AccountService accountService;
    private final PermissionService permissionService;
    private final RoleService roleService;
    private final RolesFactory rolesFactory;

    @Value("${application.exception.entity-not-found.message.template}")
    private String entityNotFoundErrorMsgTemplate;

    public AccountRolePermissionsServiceImpl(AccountService accountService,
                                             PermissionService permissionService,
                                             RoleService roleService,
                                             RolesFactory rolesFactory) {
        this.accountService = accountService;
        this.permissionService = permissionService;
        this.roleService = roleService;
        this.rolesFactory = rolesFactory;
    }

    @Override
    public Account changeRole(Long accountId, RoleName roleName) {
        Account account = accountService.getAccountByIdWithResultChecking(accountId);
        Long oldRoleId = account.getRole().getId();

        Role role = rolesFactory.getRoleByRoleName(roleName);
        account.setRole(role);
        account = accountService.update(account);

        roleService.deleteById(oldRoleId);
        return account;
    }

    /**
     *      probably will be better move this method to some 'MapperClass',
     *      but it uses only in current service, so I leaved it here
     * */
    protected Set<Permission> getPermissionsFromEnums(List<PermissionEnum> permissionEnums) {
        return permissionEnums.stream()
                .map(permissionEnum -> permissionService.getByPermissionName(permissionEnum)
                        .orElseThrow(() -> new EntityNotFoundException(String.format(
                                entityNotFoundErrorMsgTemplate, Permission.class, "name", permissionEnum))))
                .collect(Collectors.toSet());
    }

    @Override
    public Account addPermissionsToAccount(Long accountId, List<PermissionEnum> permissionEnums) {
        Account account = accountService.getAccountByIdWithResultChecking(accountId);
        Set<Permission> permissions = this.getPermissionsFromEnums(permissionEnums);
        account.getRole().getPermissions().addAll(permissions);
        return accountService.update(account);
    }

    @Override
    public Account deletePermissions(Long accountId, List<PermissionEnum> permissionEnums) {
        Account account = accountService.getAccountByIdWithResultChecking(accountId);
        Set<Permission> permissions = this.getPermissionsFromEnums(permissionEnums);
        Set<Permission> filteredPermissions = account.getRole().getPermissions().stream()
                .filter(permission -> !permissions.contains(permission))
                .collect(Collectors.toSet());

        account.getRole().setPermissions(filteredPermissions);
        return accountService.update(account);
    }
}

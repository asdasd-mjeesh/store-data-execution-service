package data_execution.data_execution.service.account;

import data_execution.data_execution.entity.account.Account;
import data_execution.data_execution.entity.account.Permission;
import data_execution.data_execution.entity.account.RoleName;

import java.util.List;

public interface AccountRolePermissionService {

    Account changeRole(Long accountId, RoleName roleName);
    boolean addPermissionsToAccount(List<Permission> permission, Long accountId);
    boolean deletePermissions(List<Permission> permission, Long accountId);
}

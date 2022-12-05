package data_execution.data_execution.service.account;

import data_execution.data_execution.entity.account.Permission;

import java.util.List;

public interface AccountRolePermissionService {

    boolean changeRole(Long roleId, Long accountId);
    boolean addPermissionsToAccount(List<Permission> permission, Long accountId);
    boolean deletePermissions(List<Permission> permission, Long accountId);
}

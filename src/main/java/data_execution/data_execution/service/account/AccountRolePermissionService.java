package data_execution.data_execution.service.account;

import data_execution.data_execution.entity.account.Account;
import data_execution.data_execution.entity.account.PermissionEnum;
import data_execution.data_execution.entity.account.RoleName;

import java.util.List;

public interface AccountRolePermissionService {

    Account changeRole(Long accountId, RoleName roleName);
    Account addPermissionsToAccount(Long accountId, List<PermissionEnum> permissionsEnums);
    Account deletePermissions(Long accountId, List<PermissionEnum> permissionsEnums);
}

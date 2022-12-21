package data_execution.data_execution.service.factory.roles;

import data_execution.data_execution.persistance.entity.account.Role;
import data_execution.data_execution.persistance.entity.account.RoleName;

public interface RolesFactory {
    Role getRoleByRoleName(RoleName roleName);
}

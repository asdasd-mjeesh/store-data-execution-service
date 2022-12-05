package data_execution.data_execution.service.factory.roles;

import data_execution.data_execution.entity.account.Role;
import data_execution.data_execution.entity.account.RoleName;
import data_execution.data_execution.service.factory.permissions.DefaultPermissionObjectFactory;
import org.springframework.stereotype.Service;

@Service
public class DefaultRolesFactory implements RolesFactory{
    private final DefaultPermissionObjectFactory permissionObjectFactory;

    public DefaultRolesFactory(DefaultPermissionObjectFactory permissionObjectFactory) {
        this.permissionObjectFactory = permissionObjectFactory;
    }

    @Override
    public Role getRoleByRoleName(RoleName roleName) {
        if (roleName.equals(RoleName.USER)) {
            return new Role(RoleName.USER, permissionObjectFactory.getUserPermissions());
        } else if (roleName.equals(RoleName.EMPLOYEE)) {
            return new Role(RoleName.EMPLOYEE, permissionObjectFactory.getEmployeePermissions());
        } else if (roleName.equals(RoleName.ADMIN)) {
            return new Role(RoleName.ADMIN, permissionObjectFactory.getAdminPermissions());
        }
        throw new RuntimeException(String.format("Role with roleName=%s is not presented", roleName));
    }
}

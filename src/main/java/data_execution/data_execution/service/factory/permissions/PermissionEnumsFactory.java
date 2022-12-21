package data_execution.data_execution.service.factory.permissions;

import data_execution.data_execution.persistance.entity.account.PermissionEnum;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class PermissionEnumsFactory implements PermissionFactory<PermissionEnum> {
    private final Set<PermissionEnum> USER_DEFAULT_PERMISSION_ENUMS;
    private final Set<PermissionEnum> EMPLOYEE_DEFAULT_PERMISSION_ENUMS;
    private final Set<PermissionEnum> ADMIN_DEFAULT_PERMISSION_ENUMS;

    {
        USER_DEFAULT_PERMISSION_ENUMS = new HashSet<>(Set.of(
                PermissionEnum.ACCOUNT_READ, PermissionEnum.ACCOUNT_DELETE,
                PermissionEnum.ORDER_SAVE, PermissionEnum.ORDER_READ,
                PermissionEnum.CART_READ, PermissionEnum.CART_EDIT,
                PermissionEnum.ITEM_READ,
                PermissionEnum.PRODUCER_READ));

        EMPLOYEE_DEFAULT_PERMISSION_ENUMS = new HashSet<>(USER_DEFAULT_PERMISSION_ENUMS);
        EMPLOYEE_DEFAULT_PERMISSION_ENUMS.addAll(Set.of(
                PermissionEnum.ORDER_UPDATE,
                PermissionEnum.ACCOUNT_ACCESS_CHANGE));

        ADMIN_DEFAULT_PERMISSION_ENUMS = new HashSet<>(EMPLOYEE_DEFAULT_PERMISSION_ENUMS);
        ADMIN_DEFAULT_PERMISSION_ENUMS.addAll(Set.of(
                PermissionEnum.ACCOUNT_SAVE,
                PermissionEnum.ORDER_DELETE,
                PermissionEnum.ITEM_SAVE,  PermissionEnum.ITEM_DELETE,
                PermissionEnum.PRODUCER_SAVE, PermissionEnum.PRODUCER_DELETE));
    }

    private PermissionEnumsFactory() {  }

    @Override
    public Set<PermissionEnum> getUserPermissions() {
        return USER_DEFAULT_PERMISSION_ENUMS;
    }

    @Override
    public Set<PermissionEnum> getEmployeePermissions() {
        return EMPLOYEE_DEFAULT_PERMISSION_ENUMS;
    }

    @Override
    public Set<PermissionEnum> getAdminPermissions() {
        return ADMIN_DEFAULT_PERMISSION_ENUMS;
    }
}

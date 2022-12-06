package data_execution.data_execution.service.factory.permissions;

import java.util.Set;

public interface PermissionFactory<T> {
    Set<T> getUserPermissions();

    Set<T> getEmployeePermissions();

    Set<T> getAdminPermissions();
}

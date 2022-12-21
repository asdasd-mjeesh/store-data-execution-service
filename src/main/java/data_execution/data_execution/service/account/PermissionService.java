package data_execution.data_execution.service.account;

import data_execution.data_execution.persistance.entity.account.Permission;
import data_execution.data_execution.persistance.entity.account.PermissionEnum;

import java.util.Collection;
import java.util.Optional;

public interface PermissionService {
    Optional<Permission> getByPermissionName(PermissionEnum permissionName);

    void saveAll(Collection<Permission> permissions);

    long getAllPermissionsCount();
}

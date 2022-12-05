package data_execution.data_execution.service.account;

import data_execution.data_execution.entity.account.Permission;
import data_execution.data_execution.entity.account.PermissionEnum;

import java.util.Collection;
import java.util.Optional;

public interface PermissionService {
    Permission create(Permission permission);
    Optional<Permission> getByPermissionName(PermissionEnum permissionName);
    boolean saveAll(Collection<Permission> permissions);
}

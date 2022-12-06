package data_execution.data_execution.service.factory.permissions;

import data_execution.data_execution.entity.account.Permission;
import data_execution.data_execution.entity.account.PermissionEnum;
import data_execution.data_execution.service.account.PermissionService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DefaultPermissionObjectFactory implements PermissionInitService, DatabasePermissionContextSynchronizer {
    private Set<Permission> userPermissions = new HashSet<>();
    private Set<Permission> employeePermissions = new HashSet<>();
    private Set<Permission> adminPermissions = new HashSet<>();

    private final PermissionService permissionService;
    private final PermissionEnumsFactory permissionEnumsFactory;

    public DefaultPermissionObjectFactory(PermissionService permissionService, PermissionEnumsFactory permissionEnumsFactory) {
        this.permissionService = permissionService;
        this.permissionEnumsFactory = permissionEnumsFactory;
    }

    @Override
    public void loadFromDatabase() {
        userPermissions = permissionEnumsFactory.getUserDefaultPermissionEnums().stream()
                .map(userEnPermission -> permissionService.getByPermissionName(userEnPermission).get())
                .collect(Collectors.toSet());

        employeePermissions = permissionEnumsFactory.getEmployeeDefaultPermissionEnums().stream()
                .map(employeeEnPermission -> permissionService.getByPermissionName(employeeEnPermission).get())
                .collect(Collectors.toSet());

        adminPermissions = permissionEnumsFactory.getAdminDefaultPermissionEnums().stream()
                .map(adminEnPermission -> permissionService.getByPermissionName(adminEnPermission).get())
                .collect(Collectors.toSet());
    }

    @Override
    public void uploadToDatabase() {
        PermissionEnum[] permissionEnums = PermissionEnum.values();
        Set<Permission> allPermissions = Arrays.stream(permissionEnums)
                .map(Permission::new)
                .collect(Collectors.toSet());
        permissionService.saveAll(allPermissions);
    }

    @Override
    public void init() {
        long permissionsCount = permissionService.getAllPermissionsCount();
        if (permissionsCount != PermissionEnum.values().length) {
            uploadToDatabase();
        }
        loadFromDatabase();
    }

    public Set<Permission> getUserPermissions() {
        return this.userPermissions;
    }

    public Set<Permission> getEmployeePermissions() {
        return this.employeePermissions;
    }

    public Set<Permission> getAdminPermissions() {
        return this.adminPermissions;
    }
}

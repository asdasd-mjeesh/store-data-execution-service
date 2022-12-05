package data_execution.data_execution.service.factory;

import data_execution.data_execution.entity.account.Permission;
import data_execution.data_execution.entity.account.PermissionEnum;
import data_execution.data_execution.service.account.PermissionService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DefaultPermissionObjectFactory implements
        PermissionInitService, DatabasePermissionContextSynchronizer {
    private Set<Permission> userPermissions = new HashSet<>();
    private Set<Permission> employeePermissions = new HashSet<>();
    private Set<Permission> adminPermissions = new HashSet<>();

    private final PermissionService permissionService;
    private final DefaultPermissionEnumsFactory permissionEnumsFactory;

    public DefaultPermissionObjectFactory(PermissionService permissionService, DefaultPermissionEnumsFactory permissionEnumsFactory) {
        this.permissionService = permissionService;
        this.permissionEnumsFactory = permissionEnumsFactory;
    }

    @Override
    public void loadFromDatabase() {
        var userEnPermissions = permissionEnumsFactory.getUserDefaultPermissionEnums();
        userPermissions = userEnPermissions.stream()
                .map(userEnPermission ->
                        permissionService.getByPermissionName(userEnPermission).get())
                .collect(Collectors.toSet());

        var employeeEnPermissions = permissionEnumsFactory.getUserDefaultPermissionEnums();
        employeePermissions = employeeEnPermissions.stream()
                .map(employeeEnPermission ->
                        permissionService.getByPermissionName(employeeEnPermission).get())
                .collect(Collectors.toSet());

        var adminEnPermissions = permissionEnumsFactory.getUserDefaultPermissionEnums();
        adminPermissions = adminEnPermissions.stream()
                .map(adminEnPermission ->
                        permissionService.getByPermissionName(adminEnPermission).get())
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
        Optional<Permission> databasePermission = permissionService.getByPermissionName(PermissionEnum.ACCOUNT_SAVE);
        if (databasePermission.isPresent()) {
            loadFromDatabase();
        } else {
            uploadToDatabase();
        }
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

package data_execution.data_execution.service.factory.permissions;

import data_execution.data_execution.entity.account.Permission;
import data_execution.data_execution.entity.account.PermissionEnum;
import data_execution.data_execution.repository.account.PermissionRepository;
import data_execution.data_execution.service.factory.ContextInitService;
import data_execution.data_execution.service.factory.EntityContextSynchronizer;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ContextPermissionEntityFactory implements ContextInitService, EntityContextSynchronizer, PermissionFactory<Permission> {
    private Set<Permission> userPermissions = new HashSet<>();
    private Set<Permission> employeePermissions = new HashSet<>();
    private Set<Permission> adminPermissions = new HashSet<>();

    private final PermissionRepository permissionRepository;
    private final PermissionEnumsFactory permissionEnumsFactory;

    public ContextPermissionEntityFactory(PermissionRepository permissionRepository, PermissionEnumsFactory permissionEnumsFactory) {
        this.permissionRepository = permissionRepository;
        this.permissionEnumsFactory = permissionEnumsFactory;
    }

    @Override
    public void init() {
        long permissionsCount = permissionRepository.count();
        if (permissionsCount != PermissionEnum.values().length) {
            uploadToDatabase();
        }
        loadFromDatabase();
    }

    @Override
    public void loadFromDatabase() {
        userPermissions = permissionEnumsFactory.getUserPermissions().stream()
                .map(userEnPermission -> permissionRepository.findByName(userEnPermission).get())
                .collect(Collectors.toSet());

        employeePermissions = permissionEnumsFactory.getEmployeePermissions().stream()
                .map(employeeEnPermission -> permissionRepository.findByName(employeeEnPermission).get())
                .collect(Collectors.toSet());

        adminPermissions = permissionEnumsFactory.getAdminPermissions().stream()
                .map(adminEnPermission -> permissionRepository.findByName(adminEnPermission).get())
                .collect(Collectors.toSet());
    }

    @Override
    public void uploadToDatabase() {
        PermissionEnum[] permissionEnums = PermissionEnum.values();
        Set<Permission> allPermissions = Arrays.stream(permissionEnums)
                .map(Permission::new)
                .collect(Collectors.toSet());
        permissionRepository.saveAll(allPermissions);
    }

    @Override
    public Set<Permission> getUserPermissions() {
        return this.userPermissions;
    }

    @Override
    public Set<Permission> getEmployeePermissions() {
        return this.employeePermissions;
    }

    @Override
    public Set<Permission> getAdminPermissions() {
        return this.adminPermissions;
    }

    public Permission getByEnum(PermissionEnum permissionEnum) {
        return this.adminPermissions.stream()
                .filter(permission -> permission.getName().equals(permissionEnum))
                .findAny().get();
    }
}

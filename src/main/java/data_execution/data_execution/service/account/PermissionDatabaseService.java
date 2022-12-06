package data_execution.data_execution.service.account;

import data_execution.data_execution.entity.account.Permission;
import data_execution.data_execution.entity.account.PermissionEnum;
import data_execution.data_execution.repository.account.PermissionRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class PermissionDatabaseService implements PermissionService {
    private final PermissionRepository permissionRepository;

    public PermissionDatabaseService(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    @Override
    public Optional<Permission> getByPermissionName(PermissionEnum permissionName) {
        return permissionRepository.findByName(permissionName);
    }

    @Override
    public void saveAll(Collection<Permission> permissions) {
        permissionRepository.saveAll(permissions);
    }

    @Override
    public long getAllPermissionsCount() {
        return permissionRepository.count();
    }
}

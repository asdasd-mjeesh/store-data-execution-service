package data_execution.data_execution.service.account;

import data_execution.data_execution.entity.account.Permission;
import data_execution.data_execution.repository.account.PermissionRepository;
import org.springframework.stereotype.Service;

@Service
public class PermissionDatabaseService implements PermissionService {
    private final PermissionRepository permissionRepository;

    public PermissionDatabaseService(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    @Override
    public Permission create(Permission permission) {
        return permissionRepository.save(permission);
    }
}

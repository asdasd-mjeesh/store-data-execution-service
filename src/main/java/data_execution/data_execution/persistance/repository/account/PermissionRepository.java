package data_execution.data_execution.persistance.repository.account;

import data_execution.data_execution.persistance.entity.account.Permission;
import data_execution.data_execution.persistance.entity.account.PermissionEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PermissionRepository extends JpaRepository<Permission, Long> {
    Optional<Permission> findByName(PermissionEnum name);
}

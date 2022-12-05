package data_execution.data_execution.repository.account;

import data_execution.data_execution.entity.account.Permission;
import data_execution.data_execution.entity.account.PermissionEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface PermissionRepository extends JpaRepository<Permission, Long> {
    Optional<Permission> findByName(PermissionEnum name);
}

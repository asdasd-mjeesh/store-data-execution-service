package data_execution.data_execution.repository.account;

import data_execution.data_execution.entity.account.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission, Long> {
}

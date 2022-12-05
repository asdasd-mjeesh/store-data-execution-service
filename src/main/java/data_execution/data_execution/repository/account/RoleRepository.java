package data_execution.data_execution.repository.account;

import data_execution.data_execution.entity.account.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}

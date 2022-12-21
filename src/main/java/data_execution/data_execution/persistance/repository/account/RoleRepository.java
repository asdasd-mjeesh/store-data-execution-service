package data_execution.data_execution.persistance.repository.account;

import data_execution.data_execution.persistance.entity.account.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}

package data_execution.data_execution.service.account;

import data_execution.data_execution.persistance.entity.account.Role;

import java.util.Optional;

public interface RoleService {
    Role save(Role role);

    Optional<Role> getById(Long id);

    boolean update(Role role);

    boolean deleteById(Long id);
}

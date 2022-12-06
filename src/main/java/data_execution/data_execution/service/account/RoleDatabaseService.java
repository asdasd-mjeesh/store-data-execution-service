package data_execution.data_execution.service.account;

import data_execution.data_execution.entity.account.Role;
import data_execution.data_execution.repository.account.RoleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class RoleDatabaseService implements RoleService {
    private final RoleRepository roleRepository;

    public RoleDatabaseService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role save(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Optional<Role> getById(Long id) {
        return roleRepository.findById(id);
    }

    @Override
    public boolean update(Role role) {
        var existedRole = roleRepository.findById(role.getId());
        if (existedRole.isPresent()) {
            roleRepository.save(role);
            return true;
        }
        return false;
    }

    @Transactional
    @Override
    public boolean deleteById(Long id) {
        roleRepository.deleteById(id);
        return roleRepository.findById(id).isEmpty();
    }
}

package data_execution.data_execution.service.factory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import data_execution.data_execution.entity.account.Permission;
import data_execution.data_execution.entity.account.Role;
import data_execution.data_execution.entity.account.RoleName;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.stream.Collectors;

@Service
public class DefaultRolesFactory {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private static Role USER_ROLE;
    private static Role EMPLOYEE_ROLE;
    private static Role ADMIN_ROLE;

    private final DefaultPermissionObjectFactory permissionObjectFactory;

    public DefaultRolesFactory(DefaultPermissionObjectFactory permissionObjectFactory) {
        this.permissionObjectFactory = permissionObjectFactory;
    }

    @PostConstruct
    private void init() {
        var userPermissions = permissionObjectFactory.getUserPermissions();
        USER_ROLE = new Role(RoleName.USER, userPermissions);

        var employeePermissions = permissionObjectFactory.getEmployeePermissions();
        EMPLOYEE_ROLE = new Role(RoleName.EMPLOYEE, employeePermissions);

        var adminPermissions = permissionObjectFactory.getAdminPermissions();
        ADMIN_ROLE = new Role(RoleName.ADMIN, adminPermissions);
    }

    public Role getRoleByRoleName(RoleName roleName) {
        try {
            if (roleName.equals(RoleName.USER)) {
                return new Role(RoleName.USER, permissionObjectFactory.getUserPermissions());
            } else if (roleName.equals(RoleName.EMPLOYEE)) {
                return getEmployeeRole();
            } else if (roleName.equals(RoleName.ADMIN)) {
                return getAdminRole();
            } else {
                throw new RuntimeException(
                        String.format("Role with roleName=%s is not presented", roleName));
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public Role getUserRole() throws JsonProcessingException {
        return OBJECT_MAPPER.readValue(OBJECT_MAPPER.writeValueAsString(USER_ROLE), Role.class);
    }

    public Role getEmployeeRole() throws JsonProcessingException {
        return OBJECT_MAPPER.readValue(OBJECT_MAPPER.writeValueAsString(EMPLOYEE_ROLE), Role.class);
    }

    public Role getAdminRole() throws JsonProcessingException {
        return OBJECT_MAPPER.readValue(OBJECT_MAPPER.writeValueAsString(ADMIN_ROLE), Role.class);
    }
}

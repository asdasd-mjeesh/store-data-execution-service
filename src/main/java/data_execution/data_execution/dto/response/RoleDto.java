package data_execution.data_execution.dto.response;

import data_execution.data_execution.entity.account.Permission;
import data_execution.data_execution.entity.account.RoleName;
import lombok.Builder;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Builder
@Data
public class RoleDto {
    private RoleName name;
    private Set<Permission> permissions = new HashSet<>();
}

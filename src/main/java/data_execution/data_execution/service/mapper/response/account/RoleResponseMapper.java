package data_execution.data_execution.service.mapper.response.account;

import data_execution.data_execution.entity.account.Permission;
import data_execution.data_execution.entity.account.Role;
import data_execution.data_execution.dto.response.account.RoleResponse;
import data_execution.data_execution.service.mapper.Mapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleResponseMapper implements Mapper<RoleResponse, Role> {

    @Override
    public RoleResponse map(Role from) {
        return RoleResponse.builder()
                .name(from.getName())
                .permissions(from.getPermissions().stream()
                        .map(Permission::getName)
                        .collect(Collectors.toSet()))
                .build();
    }

    @Override
    public List<RoleResponse> map(List<Role> fromList) {
        return fromList.stream()
                .map(this::map)
                .collect(Collectors.toList());
    }
}

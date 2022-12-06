package data_execution.data_execution.service.mapper;

import data_execution.data_execution.entity.account.Permission;
import data_execution.data_execution.entity.account.Role;
import data_execution.data_execution.dto.response.RoleDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleMapper implements Mapper<RoleDto, Role> {

    @Override
    public RoleDto map(Role from) {
        return RoleDto.builder()
                .name(from.getName())
                .permissions(from.getPermissions().stream()
                        .map(Permission::getName)
                        .collect(Collectors.toSet()))
                .build();
    }

    @Override
    public List<RoleDto> map(List<Role> fromList) {
        return fromList.stream()
                .map(this::map)
                .collect(Collectors.toList());
    }
}

package data_execution.data_execution.service.mapper.account;

import data_execution.data_execution.entity.account.Account;
import data_execution.data_execution.dto.response.account.AccountDto;
import data_execution.data_execution.service.mapper.Mapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountMapper implements Mapper<AccountDto, Account> {
    private final RoleMapper roleMapper;

    public AccountMapper(RoleMapper roleMapper) {
        this.roleMapper = roleMapper;
    }

    @Override
    public AccountDto map(Account from) {
        return AccountDto.builder()
                .id(from.getId())
                .name(from.getName())
                .contact(from.getContact())
                .email(from.getEmail())
                .password(from.getPassword())
                .role(roleMapper.map(from.getRole()))
                .build();
    }

    @Override
    public List<AccountDto> map(List<Account> fromList) {
        return fromList.stream()
                .map(this::map)
                .collect(Collectors.toList());
    }
}

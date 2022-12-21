package data_execution.data_execution.service.mapper.response.outgoing.account;

import data_execution.data_execution.persistance.entity.account.Account;
import data_execution.data_execution.dto.response.outgoing.account.AccountResponse;
import data_execution.data_execution.service.mapper.Mapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountResponseMapper implements Mapper<AccountResponse, Account> {
    private final RoleResponseMapper roleResponseMapper;

    public AccountResponseMapper(RoleResponseMapper roleResponseMapper) {
        this.roleResponseMapper = roleResponseMapper;
    }

    @Override
    public AccountResponse map(Account from) {
        return AccountResponse.builder()
                .id(from.getId())
                .name(from.getName())
                .contact(from.getContact())
                .email(from.getEmail())
                .password(from.getPassword())
                .role(roleResponseMapper.map(from.getRole()))
                .registrationDate(from.getRegistrationDate())
                .status(from.getStatus())
                .build();
    }

    @Override
    public List<AccountResponse> map(List<Account> fromList) {
        return fromList.stream()
                .map(this::map)
                .collect(Collectors.toList());
    }
}

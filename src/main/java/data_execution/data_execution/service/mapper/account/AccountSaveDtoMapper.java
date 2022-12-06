package data_execution.data_execution.service.mapper.account;

import data_execution.data_execution.dto.request.account.AccountSaveDto;
import data_execution.data_execution.entity.account.Account;
import data_execution.data_execution.entity.cart.Cart;
import data_execution.data_execution.service.factory.roles.DatabaseRolesFactory;
import data_execution.data_execution.service.mapper.Mapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountSaveDtoMapper implements Mapper<Account, AccountSaveDto> {
    private final DatabaseRolesFactory rolesFactory;

    public AccountSaveDtoMapper(DatabaseRolesFactory rolesFactory) {
        this.rolesFactory = rolesFactory;
    }

    @Override
    public Account map(AccountSaveDto from) {
        return Account.builder()
                .name(from.getName())
                .contact(from.getContact())
                .email(from.getEmail())
                .password(from.getPassword())
                .cart(new Cart())
                .role(rolesFactory.getRoleByRoleName(from.getRoleName()))
                .build();
    }

    @Override
    public List<Account> map(List<AccountSaveDto> fromList) {
        return fromList.stream()
                .map(this::map)
                .collect(Collectors.toList());
    }
}

package data_execution.data_execution.service.mapper.request.account;

import data_execution.data_execution.dto.request.account.AccountRequest;
import data_execution.data_execution.entity.account.Account;
import data_execution.data_execution.entity.cart.Cart;
import data_execution.data_execution.service.factory.roles.DatabaseRolesFactory;
import data_execution.data_execution.service.mapper.Mapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountRequestMapper implements Mapper<Account, AccountRequest> {
    private final DatabaseRolesFactory rolesFactory;

    public AccountRequestMapper(DatabaseRolesFactory rolesFactory) {
        this.rolesFactory = rolesFactory;
    }

    @Override
    public Account map(AccountRequest from) {
        var account = Account.builder()
                .name(from.getName())
                .contact(from.getContact())
                .email(from.getEmail())
                .password(from.getPassword())
                .cart(new Cart())
                .role(rolesFactory.getRoleByRoleName(from.getRoleName()))
                .build();

        if (from.getId() != null) {
            account.setId(from.getId());
        }
        return account;
    }

    @Override
    public List<Account> map(List<AccountRequest> fromList) {
        return fromList.stream()
                .map(this::map)
                .collect(Collectors.toList());
    }
}

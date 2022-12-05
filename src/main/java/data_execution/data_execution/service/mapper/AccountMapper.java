package data_execution.data_execution.service.mapper;

import data_execution.data_execution.entity.account.Account;
import data_execution.data_execution.response_dto.AccountDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountMapper implements Mapper<AccountDto, Account> {

    @Override
    public AccountDto map(Account from) {
        return new AccountDto();
    }

    @Override
    public List<AccountDto> map(List<Account> fromList) {
        return null;
    }
}

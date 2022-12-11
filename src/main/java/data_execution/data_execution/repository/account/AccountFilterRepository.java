package data_execution.data_execution.repository.account;

import data_execution.data_execution.dto.filter.AccountFilter;
import data_execution.data_execution.entity.account.Account;

import java.util.List;

public interface AccountFilterRepository {
    List<Account> findByFilter(AccountFilter filter);
}

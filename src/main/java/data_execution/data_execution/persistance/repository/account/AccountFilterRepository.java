package data_execution.data_execution.persistance.repository.account;

import data_execution.data_execution.dto.filter.AccountFilter;
import data_execution.data_execution.persistance.entity.account.Account;

import java.util.List;

public interface AccountFilterRepository {
    List<Account> findByFilter(AccountFilter filter);
}

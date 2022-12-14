package data_execution.data_execution.service.account;

import data_execution.data_execution.dto.filter.AccountFilter;
import data_execution.data_execution.persistance.entity.account.Account;

import java.util.List;
import java.util.Optional;

public interface AccountService {
    Account create(Account account);

    Optional<Account> getById(Long id);

    Account getAccountByIdWithResultChecking(Long id);

    Account getByEmail(String email);

    List<Account> getByFilter(AccountFilter filter);

    boolean updateWithConfirmation(Account account);

    Account update(Account account);

    boolean deleteById(Long id);
}

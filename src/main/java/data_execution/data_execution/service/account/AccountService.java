package data_execution.data_execution.service.account;

import data_execution.data_execution.entity.account.Account;

import java.util.List;
import java.util.Optional;

public interface AccountService {
    Account create(Account account);
    Optional<Account> getById(Long id);
    Optional<Account> getByEmail(String email);
    List<Account> getAll();
    boolean updateWithConfirmation(Account account);
    Account update(Account account);
    boolean deleteById(Long id);
}

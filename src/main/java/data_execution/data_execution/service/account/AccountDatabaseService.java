package data_execution.data_execution.service.account;

import data_execution.data_execution.entity.account.Account;
import data_execution.data_execution.repository.account.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountDatabaseService implements AccountService {
    private final AccountRepository accountRepository;

    public AccountDatabaseService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Account save(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public Optional<Account> getById(Long id) {
        return accountRepository.findById(id);
    }

    @Override
    public Optional<Account> getByEmail(String email) {
        return accountRepository.findByEmail(email);
    }

    @Override
    public List<Account> getAll() {
        return accountRepository.findAll();
    }

    @Override
    public boolean update(Account account) {
        var existedAccount = accountRepository.findById(account.getId());
        if (existedAccount.isPresent()) {
            accountRepository.save(account);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteById(Long id) {
        accountRepository.deleteById(id);
        var account = accountRepository.findById(id);
        return account.isEmpty();
    }
}

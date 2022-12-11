package data_execution.data_execution.service.account;

import data_execution.data_execution.dto.filter.AccountFilter;
import data_execution.data_execution.entity.account.Account;
import data_execution.data_execution.exception.EntityNotFoundException;
import data_execution.data_execution.repository.account.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class AccountDatabaseService implements AccountService {
    private final AccountRepository accountRepository;

    public AccountDatabaseService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Account create(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public Optional<Account> getById(Long id) {
        return accountRepository.findById(id);
    }

    @Override
    public Account getAccountByIdWithResultChecking(Long id) {
        Optional<Account> account = accountRepository.findById(id);
        if (account.isEmpty()) {
            String errorMsg = String.format("Account with id=%s not found", id);
            log.error(errorMsg);
            throw new EntityNotFoundException(errorMsg);
        }
        return account.get();
    }

    @Override
    public Account getByEmail(String email) {
        Optional<Account> account = accountRepository.findByEmail(email);
        if (account.isEmpty()) {
            String errorMsg = String.format("Account with email=%s not found", email);
            log.error(errorMsg);
            throw new EntityNotFoundException(errorMsg);
        }
        return account.get();
    }

    @Override
    public List<Account> getByFilter(AccountFilter filter) {
        return accountRepository.findByFilter(filter);
    }

    @Override
    public boolean updateWithConfirmation(Account account) {
        var existedAccount = accountRepository.findById(account.getId());
        if (existedAccount.isPresent()) {
            accountRepository.save(account);
            return true;
        }
        return false;
    }

    @Override
    public Account update(Account account) {
        var existedAccount = accountRepository.findById(account.getId());
        if (existedAccount.isPresent()) {
            return accountRepository.save(account);
        }
        throw new EntityNotFoundException(String.format("Account with id=%s not found", account.getId()));
    }

    @Override
    public boolean deleteById(Long id) {
        accountRepository.deleteById(id);
        var account = accountRepository.findById(id);
        return account.isEmpty();
    }
}

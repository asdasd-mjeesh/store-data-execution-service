package data_execution.data_execution.service.account;

import data_execution.data_execution.dto.filter.AccountFilter;
import data_execution.data_execution.persistance.entity.account.Account;
import data_execution.data_execution.exception.EntityNotFoundException;
import data_execution.data_execution.persistance.repository.account.AccountRepository;
import data_execution.data_execution.service.account.confirmation.AccountConfirmationServiceCommunication;
import data_execution.data_execution.service.mapper.request.outgoing.AccountConfirmationOutgoingRequestMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class AccountDatabaseService implements AccountService {
    private final AccountRepository accountRepository;
    private final AccountConfirmationServiceCommunication confirmationServiceCommunication;
    private final AccountConfirmationOutgoingRequestMapper accountConfirmationOutgoingRequestMapper;

    public AccountDatabaseService(AccountRepository accountRepository,
                                  AccountConfirmationServiceCommunication confirmationServiceCommunication,
                                  AccountConfirmationOutgoingRequestMapper accountConfirmationOutgoingRequestMapper) {
        this.accountRepository = accountRepository;
        this.confirmationServiceCommunication = confirmationServiceCommunication;
        this.accountConfirmationOutgoingRequestMapper = accountConfirmationOutgoingRequestMapper;
    }

    @Override
    public Account create(Account account) {
        var savedAccount = accountRepository.save(account);
        var accountConfirmRequest = accountConfirmationOutgoingRequestMapper.map(savedAccount);
        confirmationServiceCommunication.sendConfirmation(accountConfirmRequest);
        return savedAccount;
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

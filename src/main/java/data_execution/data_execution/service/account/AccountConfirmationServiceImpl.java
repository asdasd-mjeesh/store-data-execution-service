package data_execution.data_execution.service.account;

import data_execution.data_execution.entity.account.AccountStatus;
import org.springframework.stereotype.Service;

@Service
public class AccountConfirmationServiceImpl implements AccountConfirmationService {
    private final AccountService accountService;

    public AccountConfirmationServiceImpl(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public boolean confirm(Long id) {
        var account = accountService.getAccountByIdWithResultChecking(id);
        account.setStatus(AccountStatus.ENABLED);
        return accountService.updateWithConfirmation(account);
    }
}

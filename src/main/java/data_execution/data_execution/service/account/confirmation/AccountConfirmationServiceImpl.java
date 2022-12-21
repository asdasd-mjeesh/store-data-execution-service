package data_execution.data_execution.service.account.confirmation;

import data_execution.data_execution.persistance.entity.account.AccountStatus;
import data_execution.data_execution.service.account.AccountService;
import org.springframework.stereotype.Service;

@Service
public class AccountConfirmationServiceImpl implements AccountConfirmationService {
    private final AccountService accountService;
    private final AccountConfirmationServiceCommunication confirmationServiceCommunication;

    public AccountConfirmationServiceImpl(AccountService accountService,
                                          AccountConfirmationServiceCommunication confirmationServiceCommunication) {
        this.accountService = accountService;
        this.confirmationServiceCommunication = confirmationServiceCommunication;
    }

    @Override
    public boolean confirm(String confirmationToken) {
        var confirmationAccount = confirmationServiceCommunication.getAccountByConfirmationToken(confirmationToken);
        var account = accountService.getByEmail(confirmationAccount.getEmail());
        account.setStatus(AccountStatus.ENABLED);
        return accountService.updateWithConfirmation(account);
    }
}

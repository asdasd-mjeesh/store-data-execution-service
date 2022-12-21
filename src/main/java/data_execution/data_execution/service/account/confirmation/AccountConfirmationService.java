package data_execution.data_execution.service.account.confirmation;

public interface AccountConfirmationService {
    boolean confirm(String confirmationToken);
}

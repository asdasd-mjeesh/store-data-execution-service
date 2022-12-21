package data_execution.data_execution.service.account.confirmation;

import data_execution.data_execution.dto.request.outgoing.AccountConfirmationRequest;
import data_execution.data_execution.dto.response.incoming.AccountConfirmationResponse;

public interface AccountConfirmationServiceCommunication {
    AccountConfirmationResponse getAccountByConfirmationToken(String token);

    void sendConfirmation(AccountConfirmationRequest account);
}

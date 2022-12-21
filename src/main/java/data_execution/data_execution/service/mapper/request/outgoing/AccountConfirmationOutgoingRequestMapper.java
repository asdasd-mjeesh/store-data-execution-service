package data_execution.data_execution.service.mapper.request.outgoing;

import data_execution.data_execution.dto.request.outgoing.AccountConfirmationRequest;
import data_execution.data_execution.persistance.entity.account.Account;
import data_execution.data_execution.service.mapper.Mapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountConfirmationOutgoingRequestMapper implements Mapper<AccountConfirmationRequest, Account> {

    @Override
    public AccountConfirmationRequest map(Account from) {
        return AccountConfirmationRequest.builder()
                .username(from.getName())
                .email(from.getEmail())
                .contact(from.getContact())
                .createdAt(from.getRegistrationDate())
                .build();
    }

    @Override
    public List<AccountConfirmationRequest> map(List<Account> fromList) {
        return fromList.stream()
                .map(this::map)
                .collect(Collectors.toList());
    }
}

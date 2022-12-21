package data_execution.data_execution.service.account.confirmation;

import com.fasterxml.jackson.databind.ObjectMapper;
import data_execution.data_execution.dto.request.outgoing.AccountConfirmationRequest;
import data_execution.data_execution.dto.response.incoming.AccountConfirmationResponse;
import data_execution.data_execution.exception.ServiceDoesntContainRequestedEntityException;
import lombok.extern.slf4j.Slf4j;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Slf4j
@Service
public class AccountConfirmationServiceCommunicationImpl implements AccountConfirmationServiceCommunication {
    private final KafkaTemplate<String, AccountConfirmationRequest> kafkaTemplate;
    private final OkHttpClient okHttpClient;
    private final ObjectMapper objectMapper;

    @Value("${http.communication.accounts.confirmation.api.url.root}")
    private String accountConfirmRootUrl;

    public AccountConfirmationServiceCommunicationImpl(KafkaTemplate<String, AccountConfirmationRequest> kafkaTemplate,
                                                       OkHttpClient okHttpClient,
                                                       ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.okHttpClient = okHttpClient;
        this.objectMapper = objectMapper;
    }

    @Override
    public AccountConfirmationResponse getAccountByConfirmationToken(String token) {
        try {
            var urlBuilder = Objects.requireNonNull(HttpUrl.parse(accountConfirmRootUrl + "/")).newBuilder();
            urlBuilder.addQueryParameter("token", token);
            String url = urlBuilder.build().toString();

            var request = new Request.Builder()
                    .get()
                    .url(url)
                    .build();
            var call = okHttpClient.newCall(request);
            var response = call.execute();

            if (response.code() == HttpStatus.OK.value()) {
                return objectMapper.readValue(Objects.requireNonNull(response.body()).string(), AccountConfirmationResponse.class);
            }
            throw new ServiceDoesntContainRequestedEntityException(
                    String.format("Service doesn't contain entity with confirmation token=%s", token));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void sendConfirmation(AccountConfirmationRequest account) {
        kafkaTemplate.send("account_confirmation", account);
        log.info("Confirmation for account={} been send", account);
    }
}

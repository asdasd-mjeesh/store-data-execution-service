package data_execution.data_execution.service.mapper.request.producer;

import data_execution.data_execution.dto.request.producer.EmailRequest;
import data_execution.data_execution.entity.producer.Email;
import data_execution.data_execution.service.mapper.Mapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmailRequestMapper implements Mapper<Email, EmailRequest> {

    @Override
    public Email map(EmailRequest from) {
        var email = Email.builder()
                .address(from.getAddress())
                .build();

        if (from.getId() != null) {
            email.setId(from.getId());
        }
        return email;
    }

    @Override
    public List<Email> map(List<EmailRequest> fromList) {
        return fromList.stream()
                .map(this::map)
                .collect(Collectors.toList());
    }
}

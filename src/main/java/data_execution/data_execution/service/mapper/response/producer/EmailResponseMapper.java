package data_execution.data_execution.service.mapper.response.producer;

import data_execution.data_execution.dto.response.producer.EmailResponse;
import data_execution.data_execution.entity.producer.Email;
import data_execution.data_execution.service.mapper.Mapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmailResponseMapper implements Mapper<EmailResponse, Email> {

    @Override
    public EmailResponse map(Email from) {
        return EmailResponse.builder()
                .id(from.getId())
                .address(from.getAddress())
                .build();
    }

    @Override
    public List<EmailResponse> map(List<Email> fromList) {
        return fromList.stream()
                .map(this::map)
                .collect(Collectors.toList());
    }
}

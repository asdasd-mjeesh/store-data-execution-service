package data_execution.data_execution.service.mapper.producer;

import data_execution.data_execution.dto.response.producer.EmailDto;
import data_execution.data_execution.entity.producer.Email;
import data_execution.data_execution.service.mapper.Mapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmailMapper implements Mapper<EmailDto, Email> {

    @Override
    public EmailDto map(Email from) {
        return EmailDto.builder()
                .id(from.getId())
                .address(from.getAddress())
                .build();
    }

    @Override
    public List<EmailDto> map(List<Email> fromList) {
        return fromList.stream()
                .map(this::map)
                .collect(Collectors.toList());
    }
}

package data_execution.data_execution.service.mapper.response.producer;

import data_execution.data_execution.dto.response.producer.ProducerResponse;
import data_execution.data_execution.entity.producer.Producer;
import data_execution.data_execution.service.mapper.Mapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProducerResponseMapper implements Mapper<ProducerResponse, Producer> {
    private final ContactResponseMapper contactResponseMapper;
    private final EmailResponseMapper emailResponseMapper;

    public ProducerResponseMapper(ContactResponseMapper contactResponseMapper, EmailResponseMapper emailResponseMapper) {
        this.contactResponseMapper = contactResponseMapper;
        this.emailResponseMapper = emailResponseMapper;
    }

    @Override
    public ProducerResponse map(Producer from) {
        return ProducerResponse.builder()
                .id(from.getId())
                .name(from.getName())
                .contacts(contactResponseMapper.map(from.getContacts()))
                .emails(emailResponseMapper.map(from.getEmails()))
                .build();
    }

    @Override
    public List<ProducerResponse> map(List<Producer> fromList) {
        return fromList.stream()
                .map(this::map)
                .collect(Collectors.toList());
    }
}

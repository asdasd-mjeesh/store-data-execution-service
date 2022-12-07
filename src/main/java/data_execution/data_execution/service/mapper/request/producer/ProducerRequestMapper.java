package data_execution.data_execution.service.mapper.request.producer;

import data_execution.data_execution.dto.request.producer.ProducerRequest;
import data_execution.data_execution.entity.producer.Producer;
import data_execution.data_execution.service.mapper.Mapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProducerRequestMapper implements Mapper<Producer, ProducerRequest> {
    private final ContactRequestMapper contactRequestMapper;
    private final EmailRequestMapper emailrequestMapper;

    public ProducerRequestMapper(ContactRequestMapper contactRequestMapper, EmailRequestMapper emailrequestMapper) {
        this.contactRequestMapper = contactRequestMapper;
        this.emailrequestMapper = emailrequestMapper;
    }

    @Override
    public Producer map(ProducerRequest from) {
        var producer = Producer.builder()
                .name(from.getName())
                .contacts(contactRequestMapper.map(from.getContacts()))
                .emails(emailrequestMapper.map(from.getEmails()))
                .build();
        if (from.getId() != null) {
            producer.setId(from.getId());
        }
        return producer;
    }

    @Override
    public List<Producer> map(List<ProducerRequest> fromList) {
        return fromList.stream()
                .map(this::map)
                .collect(Collectors.toList());
    }
}

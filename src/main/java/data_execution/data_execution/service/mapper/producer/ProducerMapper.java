package data_execution.data_execution.service.mapper.producer;

import data_execution.data_execution.dto.response.producer.ProducerDto;
import data_execution.data_execution.entity.producer.Producer;
import data_execution.data_execution.service.mapper.Mapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProducerMapper implements Mapper<ProducerDto, Producer> {
    private final ContactMapper contactMapper;
    private final EmailMapper emailMapper;

    public ProducerMapper(ContactMapper contactMapper, EmailMapper emailMapper) {
        this.contactMapper = contactMapper;
        this.emailMapper = emailMapper;
    }

    @Override
    public ProducerDto map(Producer from) {
        return ProducerDto.builder()
                .id(from.getId())
                .name(from.getName())
                .contacts(contactMapper.map(from.getContacts()))
                .emails(emailMapper.map(from.getEmails()))
                .build();
    }

    @Override
    public List<ProducerDto> map(List<Producer> fromList) {
        return fromList.stream()
                .map(this::map)
                .collect(Collectors.toList());
    }
}

package data_execution.data_execution.service.mapper.response.outgoing.producer;

import data_execution.data_execution.dto.response.outgoing.producer.ContactResponse;
import data_execution.data_execution.persistance.entity.producer.Contact;
import data_execution.data_execution.service.mapper.Mapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContactResponseMapper implements Mapper<ContactResponse, Contact> {

    @Override
    public ContactResponse map(Contact from) {
        return ContactResponse.builder()
                .id(from.getId())
                .phoneNumber(from.getPhoneNumber())
                .build();
    }

    @Override
    public List<ContactResponse> map(List<Contact> fromList) {
        return fromList.stream()
                .map(this::map)
                .collect(Collectors.toList());
    }
}

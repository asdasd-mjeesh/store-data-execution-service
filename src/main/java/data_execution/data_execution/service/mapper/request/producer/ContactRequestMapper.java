package data_execution.data_execution.service.mapper.request.producer;

import data_execution.data_execution.dto.request.producer.ContactRequest;
import data_execution.data_execution.entity.producer.Contact;
import data_execution.data_execution.service.mapper.Mapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContactRequestMapper implements Mapper<Contact, ContactRequest> {

    @Override
    public Contact map(ContactRequest from) {
        var contact = Contact.builder()
                .phoneNumber(from.getPhoneNumber())
                .build();

        if (from.getId() != null) {
            contact.setId(from.getId());
        }
        return contact;
    }

    @Override
    public List<Contact> map(List<ContactRequest> fromList) {
        return fromList.stream()
                .map(this::map)
                .collect(Collectors.toList());
    }
}

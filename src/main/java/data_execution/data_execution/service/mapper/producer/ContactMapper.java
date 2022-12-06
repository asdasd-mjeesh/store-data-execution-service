package data_execution.data_execution.service.mapper.producer;

import data_execution.data_execution.dto.response.producer.ContactDto;
import data_execution.data_execution.entity.producer.Contact;
import data_execution.data_execution.service.mapper.Mapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContactMapper implements Mapper<ContactDto, Contact> {

    @Override
    public ContactDto map(Contact from) {
        return ContactDto.builder()
                .id(from.getId())
                .phoneNumber(from.getPhoneNumber())
                .build();
    }

    @Override
    public List<ContactDto> map(List<Contact> fromList) {
        return fromList.stream()
                .map(this::map)
                .collect(Collectors.toList());
    }
}

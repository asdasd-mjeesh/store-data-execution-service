package data_execution.data_execution.dto.response.producer;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class ProducerDto {
    private Long id;
    private String name;
    private List<EmailDto> emails;
    private List<ContactDto> contacts;
}

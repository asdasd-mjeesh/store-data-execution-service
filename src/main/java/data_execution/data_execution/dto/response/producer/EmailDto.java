package data_execution.data_execution.dto.response.producer;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class EmailDto {
    private Long id;
    private String address;
}

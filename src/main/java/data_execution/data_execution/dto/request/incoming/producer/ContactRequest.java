package data_execution.data_execution.dto.request.incoming.producer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactRequest {
    private Long id;
    private String phoneNumber;
}

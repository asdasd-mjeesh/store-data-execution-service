package data_execution.data_execution.dto.filter;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProducerFilter extends BaseFilter {
    private String name;
}

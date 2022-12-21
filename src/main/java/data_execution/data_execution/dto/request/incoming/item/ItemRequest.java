package data_execution.data_execution.dto.request.incoming.item;

import data_execution.data_execution.dto.request.incoming.producer.ProducerRequest;
import data_execution.data_execution.persistance.entity.item.ItemType;
import data_execution.data_execution.persistance.entity.item.SizeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemRequest {
    private Long id;
    private String title;
    private ItemType type;
    private BigDecimal cost;
    private ProducerRequest producer;
    private List<SizeEnum> sizes;
}

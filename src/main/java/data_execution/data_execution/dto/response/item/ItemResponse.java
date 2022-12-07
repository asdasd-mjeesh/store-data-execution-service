package data_execution.data_execution.dto.response.item;

import data_execution.data_execution.dto.response.producer.ProducerResponse;
import data_execution.data_execution.entity.item.ItemType;
import data_execution.data_execution.entity.item.SizeEnum;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Builder
@Data
public class ItemResponse {
    private Long id;
    private String title;
    private ItemType type;
    private BigDecimal cost;
    private ProducerResponse producer;
    private List<SizeEnum> sizes;
}

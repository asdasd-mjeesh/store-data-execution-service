package data_execution.data_execution.dto.response.order;

import data_execution.data_execution.dto.response.item.ItemResponse;
import data_execution.data_execution.entity.item.SizeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemResponse {
    private Long id;
    private ItemResponse item;
    private SizeEnum size;
    private Integer count;
}

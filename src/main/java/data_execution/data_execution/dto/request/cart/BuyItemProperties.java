package data_execution.data_execution.dto.request.cart;

import data_execution.data_execution.entity.item.SizeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BuyItemProperties {
    private Long itemId;
    private Integer count;
    private SizeEnum size;
}

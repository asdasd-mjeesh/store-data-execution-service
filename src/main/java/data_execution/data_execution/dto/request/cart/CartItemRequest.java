package data_execution.data_execution.dto.request.cart;

import data_execution.data_execution.dto.request.item.ItemRequest;
import data_execution.data_execution.entity.item.SizeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItemRequest {
    private Long id;
    private ItemRequest itemRequest;
    private SizeEnum size;
    private Integer count;
}

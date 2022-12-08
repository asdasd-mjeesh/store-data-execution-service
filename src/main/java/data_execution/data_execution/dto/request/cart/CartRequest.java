package data_execution.data_execution.dto.request.cart;

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
public class CartRequest {
    private Long id;
    private List<CartItemRequest> cartItems;
    private BigDecimal currentTotalPrice;
}

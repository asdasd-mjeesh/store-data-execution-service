package data_execution.data_execution.dto.response.cart;

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
public class CartResponse {
    private Long id;
    private List<CartItemResponse> cartItems;
    private BigDecimal currentTotalPrice;
}

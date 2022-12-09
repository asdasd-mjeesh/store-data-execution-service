package data_execution.data_execution.dto.response.order;

import data_execution.data_execution.entity.order.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {
    private Long id;
    private List<OrderItemResponse> orderItems;
    private BigDecimal totalPrice;
    private LocalDateTime buyDate;
    private OrderStatus status;
}

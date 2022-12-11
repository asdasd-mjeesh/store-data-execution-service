package data_execution.data_execution.dto.filter;

import data_execution.data_execution.entity.order.OrderStatus;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderFilter extends BaseFilter {
    private String accountName;
    private BigDecimal minTotalPrice;
    private BigDecimal maxTotalPrice;
    private LocalDateTime minBuyDate;
    private LocalDateTime maxBuyDate;
    private OrderStatus status;
}

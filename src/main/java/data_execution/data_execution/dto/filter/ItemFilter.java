package data_execution.data_execution.dto.filter;

import data_execution.data_execution.persistance.entity.item.ItemType;
import lombok.*;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemFilter extends BaseFilter {
    private String title;
    private ItemType type;
    private BigDecimal minCost;
    private BigDecimal maxCost;
    private String producerName;
}

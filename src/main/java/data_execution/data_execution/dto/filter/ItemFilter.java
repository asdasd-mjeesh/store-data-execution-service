package data_execution.data_execution.dto.filter;

import data_execution.data_execution.entity.item.ItemType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

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

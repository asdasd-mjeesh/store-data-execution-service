package data_execution.data_execution.persistance.entity.order;

import com.fasterxml.jackson.annotation.JsonIgnore;
import data_execution.data_execution.persistance.entity.BaseEntity;
import data_execution.data_execution.persistance.entity.item.Item;
import data_execution.data_execution.persistance.entity.item.Size;
import lombok.*;

import javax.persistence.*;

@Builder
@EqualsAndHashCode(callSuper = true, exclude = "order")
@ToString(exclude = "order")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(schema = "store_domain", name = "order_item")
public class OrderItem extends BaseEntity {
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    @OneToOne
    @JoinColumn(name = "size_id")
    private Size size;

    @Column(name = "count")
    private Integer count;
}

package data_execution.data_execution.persistance.entity.cart;

import data_execution.data_execution.persistance.entity.BaseEntity;
import data_execution.data_execution.persistance.entity.item.Item;
import data_execution.data_execution.persistance.entity.item.Size;
import lombok.*;

import javax.persistence.*;

@Builder
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(schema = "store_domain", name = "cart_item")
public class CartItem extends BaseEntity {
    @OneToOne
    @JoinColumn(name = "item_id")
    private Item item;
    @OneToOne
    @JoinColumn(name = "size_id")
    private Size size;
    @Column(name = "count")
    private Integer count;
}

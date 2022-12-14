package data_execution.data_execution.entity.cart;

import data_execution.data_execution.entity.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Builder
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(schema = "store_domain", name = "cart")
public class Cart extends BaseEntity {
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "cart_id")
    private Set<CartItem> cartItems;

    @Column(name = "current_total_price")
    private BigDecimal currentTotalPrice = BigDecimal.ZERO;

    public void calculateCurrentTotalPrice() {
//        this.currentTotalPrice = cartItems.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
        this.currentTotalPrice = BigDecimal.TEN;
    }
}

package data_execution.data_execution.entity.cart;

import data_execution.data_execution.entity.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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
    private List<CartItem> cartItems = new ArrayList<>();

    @Column(name = "current_total_price")
    private BigDecimal currentTotalPrice = BigDecimal.ZERO;

    public void calculateCurrentTotalPrice() {
        cartItems.forEach(cartItem ->
            currentTotalPrice = currentTotalPrice.add(
                    cartItem.getItem().getCost()
                            .multiply(BigDecimal.valueOf(cartItem.getCount()))));
    }
}

package data_execution.data_execution.persistance.entity.order;

import data_execution.data_execution.persistance.entity.BaseEntity;
import data_execution.data_execution.persistance.entity.account.Account;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(schema = "store_domain", name = "orders")
public class Order extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private Account account;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private List<OrderItem> orderItems = new ArrayList<>();

    @Column(name = "total_price")
    private BigDecimal totalPrice;
    @Column(name = "buy_date")
    private LocalDateTime buyDate;
    @Column(name = "status")
    @Enumerated(value = EnumType.STRING)
    private OrderStatus status;
}

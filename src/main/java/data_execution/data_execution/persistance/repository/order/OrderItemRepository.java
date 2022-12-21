package data_execution.data_execution.persistance.repository.order;

import data_execution.data_execution.persistance.entity.order.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}

package data_execution.data_execution.repository.order;

import data_execution.data_execution.entity.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}

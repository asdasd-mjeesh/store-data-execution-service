package data_execution.data_execution.persistance.repository.order;

import data_execution.data_execution.persistance.entity.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long>, OrderFilterRepository {
}

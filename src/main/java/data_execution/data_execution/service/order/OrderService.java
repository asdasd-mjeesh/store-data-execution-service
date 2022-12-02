package data_execution.data_execution.service.order;

import data_execution.data_execution.entity.order.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    Order save(Order order);
    Optional<Order> getById(Long id);
    List<Order> getAll();
    boolean update(Order order);
    boolean deleteById(Long id);
}

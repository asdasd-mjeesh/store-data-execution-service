package data_execution.data_execution.service.order;

import data_execution.data_execution.dto.filter.OrderFilter;
import data_execution.data_execution.entity.order.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    Order save(Order order);
    Order getByIdWithResultChecking(Long id);
    Optional<Order> getById(Long id);
    List<Order> getByFilter(OrderFilter filter);
    boolean update(Order order);
    boolean deleteById(Long id);
}

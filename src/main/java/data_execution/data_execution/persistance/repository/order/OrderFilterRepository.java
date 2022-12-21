package data_execution.data_execution.persistance.repository.order;

import data_execution.data_execution.dto.filter.OrderFilter;
import data_execution.data_execution.persistance.entity.order.Order;

import java.util.List;

public interface OrderFilterRepository {
    List<Order> findByFilter(OrderFilter filter);
}

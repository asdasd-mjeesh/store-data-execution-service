package data_execution.data_execution.repository.order;

import data_execution.data_execution.dto.filter.OrderFilter;
import data_execution.data_execution.entity.order.Order;

import java.util.List;

public interface OrderFilterRepository {
    List<Order> findByFilter(OrderFilter filter);
}

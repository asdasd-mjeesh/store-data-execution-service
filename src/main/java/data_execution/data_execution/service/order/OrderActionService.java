package data_execution.data_execution.service.order;

import data_execution.data_execution.persistance.entity.order.Order;
import data_execution.data_execution.persistance.entity.order.OrderStatus;

public interface OrderActionService {
    Order changeStatus(Long orderId, OrderStatus newStatus);
}

package data_execution.data_execution.service.order;

import data_execution.data_execution.entity.order.Order;

public interface OrderCreatorService {
    Order createOrder(Long accountId);
}

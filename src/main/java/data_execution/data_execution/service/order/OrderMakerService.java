package data_execution.data_execution.service.order;

import data_execution.data_execution.entity.order.Order;

public interface OrderMakerService {
    Order buy(Long accountId);
}

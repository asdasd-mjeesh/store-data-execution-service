package data_execution.data_execution.service.order;

import data_execution.data_execution.persistance.entity.order.Order;
import data_execution.data_execution.persistance.entity.order.OrderStatus;
import org.springframework.stereotype.Service;

@Service
public class OrderActionServiceImpl implements OrderActionService {
    private final OrderService orderService;

    public OrderActionServiceImpl(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public Order changeStatus(Long orderId, OrderStatus newStatus) {
        var targetOrder = orderService.getByIdWithResultChecking(orderId);
        targetOrder.setStatus(newStatus);
        return orderService.save(targetOrder);
    }
}

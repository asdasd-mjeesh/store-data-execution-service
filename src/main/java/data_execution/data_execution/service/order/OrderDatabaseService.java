package data_execution.data_execution.service.order;

import data_execution.data_execution.entity.order.Order;
import data_execution.data_execution.repository.order.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderDatabaseService implements OrderService {
    private final OrderRepository orderRepository;

    public OrderDatabaseService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Optional<Order> getById(Long id) {
        return orderRepository.findById(id);
    }

    @Override
    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    @Override
    public boolean update(Order order) {
        var existedOrder = orderRepository.findById(order.getId());
        if (existedOrder.isPresent()) {
            orderRepository.save(order);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteById(Long id) {
        orderRepository.deleteById(id);
        var order = orderRepository.findById(id);
        return order.isEmpty();
    }
}

package data_execution.data_execution.service.order;

import data_execution.data_execution.entity.order.Order;
import data_execution.data_execution.exception.EntityNotFoundException;
import data_execution.data_execution.repository.order.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
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
    public Order getByIdWithResultChecking(Long id) {
        var order = orderRepository.findById(id);
        if (order.isEmpty()) {
            String errorMsg = String.format("Order with id=%s not found", id);
            log.error(errorMsg);
            throw new EntityNotFoundException(errorMsg);
        }
        return order.get();
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

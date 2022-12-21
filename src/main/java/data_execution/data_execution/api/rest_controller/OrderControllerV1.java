package data_execution.data_execution.api.rest_controller;

import data_execution.data_execution.dto.filter.OrderFilter;
import data_execution.data_execution.dto.response.outgoing.order.OrderResponse;
import data_execution.data_execution.persistance.entity.order.OrderStatus;
import data_execution.data_execution.service.mapper.response.outgoing.order.OrderResponseMapper;
import data_execution.data_execution.service.order.OrderActionService;
import data_execution.data_execution.service.order.OrderCreatorService;
import data_execution.data_execution.service.order.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderControllerV1 {
    private final OrderService orderService;
    private final OrderCreatorService orderCreatorService;
    private final OrderResponseMapper orderResponseMapper;
    private final OrderActionService orderActionService;

    public OrderControllerV1(OrderService orderService,
                             OrderCreatorService orderCreatorService,
                             OrderResponseMapper orderResponseMapper,
                             OrderActionService orderActionService) {
        this.orderService = orderService;
        this.orderCreatorService = orderCreatorService;
        this.orderResponseMapper = orderResponseMapper;
        this.orderActionService = orderActionService;
    }

    @PostMapping("/")
    public ResponseEntity<OrderResponse> createOrder(@RequestParam(name = "accountId") Long accountId) {
        var order = orderCreatorService.createOrder(accountId);
        var orderResponse = orderResponseMapper.map(order);
        return new ResponseEntity<>(orderResponse, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> getById(@PathVariable(name = "id") Long id) {
        var order = orderService.getByIdWithResultChecking(id);
        var orderResponse = orderResponseMapper.map(order);
        return ResponseEntity.ok(orderResponse);
    }

    @PutMapping("/filter")
    public ResponseEntity<List<OrderResponse>> getByFilter(@RequestBody OrderFilter filter) {
        var orders = orderService.getByFilter(filter);
        var ordersResponse = orderResponseMapper.map(orders);
        return ResponseEntity.ok(ordersResponse);
    }

    @PatchMapping("/changeStatus")
    public ResponseEntity<OrderResponse> changeStatus(@RequestParam(name = "orderId") Long orderId,
                                                      @RequestParam(name = "status") OrderStatus status) {
        var order = orderActionService.changeStatus(orderId, status);
        var orderResponse = orderResponseMapper.map(order);
        return ResponseEntity.ok(orderResponse);
    }
}

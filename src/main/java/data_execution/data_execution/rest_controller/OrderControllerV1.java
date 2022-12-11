package data_execution.data_execution.rest_controller;

import data_execution.data_execution.dto.filter.OrderFilter;
import data_execution.data_execution.dto.response.order.OrderResponse;
import data_execution.data_execution.entity.order.OrderStatus;
import data_execution.data_execution.service.mapper.response.order.OrderResponseMapper;
import data_execution.data_execution.service.order.OrderActionService;
import data_execution.data_execution.service.order.OrderCreatorService;
import data_execution.data_execution.service.order.OrderService;
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

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> getById(@PathVariable(name = "id") Long id) {
        var order = orderService.getByIdWithResultChecking(id);
        var orderResponse = orderResponseMapper.map(order);
        return ResponseEntity.ok(orderResponse);
    }

    @GetMapping("/filter")
    public ResponseEntity<List<OrderResponse>> getByFilter(@RequestBody OrderFilter filter) {
        var orders = orderService.getByFilter(filter);
        var ordersResponse = orderResponseMapper.map(orders);
        return ResponseEntity.ok(ordersResponse);
    }

    @PostMapping("/create")
    public ResponseEntity<OrderResponse> buyCartItems(@RequestParam(name = "accountId") Long accountId) {
        var order = orderCreatorService.createOrder(accountId);
        var orderResponse = orderResponseMapper.map(order);
        return ResponseEntity.ok(orderResponse);
    }

    @PatchMapping("/changeStatus")
    public ResponseEntity<OrderResponse> changeStatus(@RequestParam(name = "orderId") Long orderId,
                                                      @RequestParam(name = "orderStatus") OrderStatus orderStatus) {
        var order = orderActionService.changeStatus(orderId, orderStatus);
        var orderResponse = orderResponseMapper.map(order);
        return ResponseEntity.ok(orderResponse);
    }
}

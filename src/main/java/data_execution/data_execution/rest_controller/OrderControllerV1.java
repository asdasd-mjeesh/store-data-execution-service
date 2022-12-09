package data_execution.data_execution.rest_controller;

import data_execution.data_execution.entity.order.Order;
import data_execution.data_execution.service.order.OrderMakerService;
import data_execution.data_execution.service.order.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderControllerV1 {
    private final OrderService orderService;
    private final OrderMakerService orderMakerService;

    public OrderControllerV1(OrderService orderService, OrderMakerService orderMakerService) {
        this.orderService = orderService;
        this.orderMakerService = orderMakerService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getById(@PathVariable(name = "id") Long id) {
        var order = orderService.getById(id).get();
        return ResponseEntity.ok(order);
    }

    @PostMapping("/buy")
    public ResponseEntity<Order> buyCartItems(@RequestParam(name = "accountId") Long accountId) {
        var order = orderMakerService.buy(accountId);
        return ResponseEntity.ok(order);
    }
}

package data_execution.data_execution.service.order;

import data_execution.data_execution.entity.account.Account;
import data_execution.data_execution.entity.cart.Cart;
import data_execution.data_execution.entity.order.Order;
import data_execution.data_execution.entity.order.OrderItem;
import data_execution.data_execution.entity.order.OrderStatusEnum;
import data_execution.data_execution.service.account.AccountService;
import data_execution.data_execution.service.cart.CartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class OrderMakerServiceImpl implements OrderMakerService {
    private final CartService cartService;
    private final OrderService orderService;
    private final AccountService accountService;

    public OrderMakerServiceImpl(CartService cartService, OrderService orderService, AccountService accountService) {
        this.cartService = cartService;
        this.orderService = orderService;
        this.accountService = accountService;
    }

    protected Order buildOrderFromCart(Cart cart, Account account) {
        Order order = Order.builder()
                .account(account)
                .orderItems(null)
                .status(OrderStatusEnum.IN_PROCESS)
                .buyDate(LocalDateTime.now())
                .build();
        List<OrderItem> orderItems = cart.getCartItems().stream()
                .map(cartItem -> OrderItem.builder()
                        .order(order)
                        .item(cartItem.getItem())
                        .size(cartItem.getSize())
                        .count(cartItem.getCount())
                        .build())
                .collect(Collectors.toList());
        order.setOrderItems(orderItems);

        double doubleTotalPrice = orderItems.stream().
                mapToDouble(item -> item.getItem().getCost().doubleValue())
                .sum();
        order.setTotalPrice(BigDecimal.valueOf(doubleTotalPrice));

        return order;
    }

    @Override
    public Order buy(Long accountId) {
        var account = accountService.getAccountByIdWithResultChecking(accountId);
        var cart = account.getCart();
        account.getCart().getCartItems().forEach(System.out::println);

        var order = this.buildOrderFromCart(cart, account);
        order = orderService.save(order);

        cart.getCartItems().removeAll(cart.getCartItems());
        cart.calculateCurrentTotalPrice();
        cartService.update(cart);

        return order;
    }
}

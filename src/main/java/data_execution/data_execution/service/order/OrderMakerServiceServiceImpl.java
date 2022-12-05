package data_execution.data_execution.service.order;

import data_execution.data_execution.entity.account.Account;
import data_execution.data_execution.entity.cart.Cart;
import data_execution.data_execution.entity.order.Order;
import data_execution.data_execution.entity.order.OrderItem;
import data_execution.data_execution.entity.order.OrderStatusEnum;
import data_execution.data_execution.exception.EntityNotFoundException;
import data_execution.data_execution.service.account.AccountService;
import data_execution.data_execution.service.cart.CartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.stream.Collectors;

@Service
@Slf4j
public class OrderMakerServiceServiceImpl implements OrderMakerService {
    private final CartService cartService;
    private final OrderService orderService;
    private final AccountService accountService;

    public OrderMakerServiceServiceImpl(CartService cartService, OrderService orderService, AccountService accountService) {
        this.cartService = cartService;
        this.orderService = orderService;
        this.accountService = accountService;
    }

    protected Order buildOrderFromCart(Cart cart, Account account) {
        var order = Order.builder()
                .account(account)
                .orderItems(null)
                .status(OrderStatusEnum.IN_PROCESS)
                .buyDate(LocalDateTime.now())
                .build();
        var orderItems = cart.getCartItems().stream()
                .map(cartItem -> OrderItem.builder()
                        .order(order)
                        .item(cartItem.getItem())
                        .size(cartItem.getSize())
                        .count(cartItem.getCount())
                        .build())
                .collect(Collectors.toList());
        order.setOrderItems(orderItems);
        return order;
    }

    @Override
    public void buy(Long accountId) {
        var account = accountService.getById(accountId);
        if (account.isEmpty()) {
            String errorMsg = String.format("Account with id=%s not found", accountId);
            log.error(errorMsg);
            throw new EntityNotFoundException(errorMsg);
        }
        var cart = account.get().getCart();
        if (cart == null || cart.getCartItems().isEmpty()) {
            String errorMsg = String.format("Cart with id=%s is empty", accountId);
            log.error(errorMsg);
            throw new RuntimeException(errorMsg);
        }

        Order order = this.buildOrderFromCart(cart, account.get());
        orderService.save(order);
        cart.setCartItems(new ArrayList<>());

        boolean isUpdated = cartService.update(cart);
        if (!isUpdated) {
            log.warn("Cart with id=%s wasn't updated");
        }
    }
}

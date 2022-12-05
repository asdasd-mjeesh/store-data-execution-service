package data_execution.data_execution.service.cart;

import data_execution.data_execution.entity.cart.Cart;

import java.util.Optional;

public interface CartService {
    Optional<Cart> getByAccountId(Long accountId);
    boolean update(Cart cart);
}

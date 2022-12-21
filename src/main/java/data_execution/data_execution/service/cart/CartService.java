package data_execution.data_execution.service.cart;

import data_execution.data_execution.persistance.entity.cart.Cart;

import java.util.Optional;

public interface CartService {
    Optional<Cart> getByAccountId(Long accountId);
    Cart getCartByAccountIdWithResultChecking(Long accountId);
    Cart update(Cart cart);
    boolean updateWithConfirmation(Cart cart);
}

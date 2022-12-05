package data_execution.data_execution.repository.cart;

import data_execution.data_execution.entity.cart.Cart;

import java.util.Optional;

public interface CustomCartRepository {

    Optional<Cart> getByAccountId(Long accountId);
}

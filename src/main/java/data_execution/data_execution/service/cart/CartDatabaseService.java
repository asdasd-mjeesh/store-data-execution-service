package data_execution.data_execution.service.cart;

import data_execution.data_execution.entity.cart.Cart;
import data_execution.data_execution.repository.cart.CartRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartDatabaseService implements CartService {
    private final CartRepository cartRepository;

    public CartDatabaseService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public Optional<Cart> getByAccountId(Long accountId) {
//        return cartRepository.getByAccountId(accountId);
        return null;
    }

    @Override
    public boolean update(Cart cart) {
        var existedCart = cartRepository.findById(cart.getId());
        if (existedCart.isPresent()) {
            cartRepository.save(cart);
            return true;
        }
        return false;
    }
}

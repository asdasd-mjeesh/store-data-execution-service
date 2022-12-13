package data_execution.data_execution.service.cart;

import data_execution.data_execution.entity.cart.Cart;
import data_execution.data_execution.exception.EntityNotFoundException;
import data_execution.data_execution.repository.cart.CartRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class CartDatabaseService implements CartService {
    private final CartRepository cartRepository;

    public CartDatabaseService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public Optional<Cart> getByAccountId(Long accountId) {
        return cartRepository.findByAccountId(accountId);
    }

    @Override
    public Cart getCartByAccountIdWithResultChecking(Long accountId) {
        var cart = cartRepository.findByAccountId(accountId);
        if (cart.isEmpty()) {
            String errorMsg = String.format("Cart with account_id=%s not found", accountId);
            log.error(errorMsg);
            throw new EntityNotFoundException(errorMsg);
        }
        return cart.get();
    }

    @Override
    public Cart update(Cart cart) {
        return cartRepository.save(cart);
    }

    @Override
    public boolean updateWithConfirmation(Cart cart) {
        var existedCart = cartRepository.findById(cart.getId());
        if (existedCart.isPresent()) {
            cartRepository.save(cart);
            return true;
        }
        return false;
    }
}

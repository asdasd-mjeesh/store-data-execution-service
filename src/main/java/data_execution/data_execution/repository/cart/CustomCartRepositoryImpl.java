package data_execution.data_execution.repository.cart;

import data_execution.data_execution.entity.cart.Cart;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

public class CustomCartRepositoryImpl implements CustomCartRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<Cart> getByAccountId(Long accountId) {
        String SELECT_BY_ACCOUNT_ID =
                "SELECT c.*, ci.*" +
                "FROM cart c" +
                "JOIN account a ON c.id = a.cart_id" +
                "JOIN cart_item ci ON c.id = ci.cart_id" +
                "WHERE a.id = ?1";
        Cart cart = (Cart) entityManager.createNativeQuery(SELECT_BY_ACCOUNT_ID, Cart.class)
                .setParameter(1, accountId).getSingleResult();
        System.out.println(cart);
        return Optional.ofNullable(cart);
    }
}

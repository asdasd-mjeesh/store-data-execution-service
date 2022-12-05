package data_execution.data_execution.repository.cart;

import data_execution.data_execution.entity.cart.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
//    String SELECT_BY_ACCOUNT_ID =
//            "SELECT c.*, ci.*" +
//            "FROM cart c" +
//            "JOIN account a ON c.id = a.cart_id" +
//            "JOIN cart_item ci ON c.id = ci.cart_id" +
//            "WHERE a.id = :accountId";
//
//    @Query(value =
//            "select c.* from cart c where c.id = :accountId",
//            nativeQuery = true)
//    Optional<Cart> getByAccountId(@Param("accountId") Long accountId);
}

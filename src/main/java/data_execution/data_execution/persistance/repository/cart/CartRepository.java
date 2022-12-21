package data_execution.data_execution.persistance.repository.cart;

import data_execution.data_execution.persistance.entity.cart.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {

    @Query("SELECT c FROM Cart c JOIN Account a ON c.id = a.cart.id WHERE a.id = :accountId")
    Optional<Cart> findByAccountId(@Param("accountId") Long accountId);
}

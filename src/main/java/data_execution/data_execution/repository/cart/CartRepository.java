package data_execution.data_execution.repository.cart;

import data_execution.data_execution.entity.cart.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
}

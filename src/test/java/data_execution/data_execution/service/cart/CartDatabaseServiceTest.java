package data_execution.data_execution.service.cart;

import data_execution.data_execution.IntegrationTestBase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class CartDatabaseServiceTest extends IntegrationTestBase {
    @Autowired
    private CartDatabaseService cartDatabaseService;

    @Test
    void getCartByAccountIdWithResultChecking() {
        var cart = cartDatabaseService.getCartByAccountIdWithResultChecking(3L);
        System.out.println(cart);
        cart.getCartItems().forEach(System.out::println);
    }
}
package data_execution.data_execution.repository.cart;

import data_execution.data_execution.IntegrationTestBase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class CartRepositoryTest extends IntegrationTestBase {

    @Autowired
    private CartRepository cartRepository;

//    @Test
//    void getByAccountId() {
//        var cart = cartRepository.getByAccountId(3L);
//        System.out.println(cart);
//    }
}
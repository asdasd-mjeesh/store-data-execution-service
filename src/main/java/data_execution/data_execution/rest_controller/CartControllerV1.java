package data_execution.data_execution.rest_controller;

import data_execution.data_execution.entity.cart.Cart;
import data_execution.data_execution.service.cart.CartInteractionService;
import data_execution.data_execution.service.cart.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/carts")
public class CartControllerV1 {
    private final CartService cartService;
    private final CartInteractionService cartInteractionService;

    public CartControllerV1(CartService cartService, CartInteractionService cartInteractionService) {
        this.cartService = cartService;
        this.cartInteractionService = cartInteractionService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cart> getById(@PathVariable(name = "id") Long accountId) {
        return ResponseEntity.ok(cartService.getByAccountId(accountId).get());
    }
}

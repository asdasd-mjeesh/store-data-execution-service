package data_execution.data_execution.rest_controller;

import data_execution.data_execution.dto.request.cart.BuyItemProperties;
import data_execution.data_execution.entity.cart.Cart;
import data_execution.data_execution.service.cart.CartActionService;
import data_execution.data_execution.service.cart.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/carts")
public class CartControllerV1 {
    private final CartService cartService;
    private final CartActionService cartActionService;

    public CartControllerV1(CartService cartService, CartActionService cartActionService) {
        this.cartService = cartService;
        this.cartActionService = cartActionService;
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<Cart> getByAccountId(@PathVariable(name = "accountId") Long accountId) {
        return ResponseEntity.ok(cartService.getByAccountId(accountId).get());
    }

    @PostMapping("/addItem")
    public ResponseEntity<Cart> addItem(@RequestParam(name = "accountId") Long accountId,
                                        @RequestBody BuyItemProperties buyItemProperties) {
        var updatedCart = cartActionService.addItem(accountId, buyItemProperties);
        return ResponseEntity.ok(updatedCart);
    }

    @PatchMapping("/editItem")
    public ResponseEntity<Cart> editItem(@RequestParam(name = "accountId") Long accountId,
                                         @RequestBody BuyItemProperties buyItemProperties) {
        var updatedCart = cartActionService.editItem(accountId, buyItemProperties);
        return ResponseEntity.ok(updatedCart);
    }

    @DeleteMapping("/deleteItem")
    public ResponseEntity<Cart> deleteItem(@RequestParam(name = "accountId") Long accountId,
                                           @RequestParam(name = "cartItemId") Long cartItemId) {
        var updatedCart = cartActionService.deleteItem(accountId, cartItemId);
        return ResponseEntity.ok(updatedCart);
    }

    @DeleteMapping("/deleteAllItems")
    public ResponseEntity<Cart> deleteAllItems(@RequestParam(name = "accountId") Long accountId) {
        var updatedCart = cartActionService.deleteAllItems(accountId);
        return ResponseEntity.ok(updatedCart);
    }
}

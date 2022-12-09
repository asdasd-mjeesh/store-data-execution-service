package data_execution.data_execution.rest_controller;

import data_execution.data_execution.dto.request.cart.BuyItemProperties;
import data_execution.data_execution.dto.response.cart.CartResponse;
import data_execution.data_execution.service.cart.CartActionService;
import data_execution.data_execution.service.cart.CartService;
import data_execution.data_execution.service.mapper.response.cart.CartResponseMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/carts")
public class CartControllerV1 {
    private final CartService cartService;
    private final CartActionService cartActionService;
    private final CartResponseMapper cartResponseMapper;

    public CartControllerV1(CartService cartService,
                            CartActionService cartActionService,
                            CartResponseMapper cartResponseMapper) {
        this.cartService = cartService;
        this.cartActionService = cartActionService;
        this.cartResponseMapper = cartResponseMapper;
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<CartResponse> getByAccountId(@PathVariable(name = "accountId") Long accountId) {
        var cart = cartService.getCartByAccountIdWithResultChecking(accountId);
        var cartResponse = cartResponseMapper.map(cart);
        return ResponseEntity.ok(cartResponse);
    }

    @PostMapping("/addItem")
    public ResponseEntity<CartResponse> addItem(@RequestParam(name = "accountId") Long accountId,
                                        @RequestBody BuyItemProperties buyItemProperties) {
        var updatedCart = cartActionService.addItem(accountId, buyItemProperties);
        var updatedCartDto = cartResponseMapper.map(updatedCart);
        return ResponseEntity.ok(updatedCartDto);
    }

    @PatchMapping("/editItem")
    public ResponseEntity<CartResponse> editItem(@RequestParam(name = "accountId") Long accountId,
                                         @RequestParam(name = "cartItemId") Long cartItemId,
                                         @RequestBody BuyItemProperties buyItemProperties) {
        var updatedCart = cartActionService.editItem(accountId, cartItemId, buyItemProperties);
        var updatedCartDto = cartResponseMapper.map(updatedCart);
        return ResponseEntity.ok(updatedCartDto);
    }

    @DeleteMapping("/deleteItem")
    public ResponseEntity<CartResponse> deleteItem(@RequestParam(name = "accountId") Long accountId,
                                           @RequestParam(name = "cartItemId") Long cartItemId) {
        var updatedCart = cartActionService.deleteItem(accountId, cartItemId);
        var updatedCartDto = cartResponseMapper.map(updatedCart);
        return ResponseEntity.ok(updatedCartDto);
    }

    @DeleteMapping("/deleteAllItems")
    public ResponseEntity<CartResponse> deleteAllItems(@RequestParam(name = "accountId") Long accountId) {
        var updatedCart = cartActionService.deleteAllItems(accountId);
        var updatedCartDto = cartResponseMapper.map(updatedCart);
        return ResponseEntity.ok(updatedCartDto);
    }
}

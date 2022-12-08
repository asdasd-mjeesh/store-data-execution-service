package data_execution.data_execution.service.cart;

import data_execution.data_execution.dto.request.cart.BuyItemProperties;
import data_execution.data_execution.entity.cart.Cart;
import data_execution.data_execution.entity.cart.CartItem;
import data_execution.data_execution.entity.item.Item;
import data_execution.data_execution.entity.item.Size;
import data_execution.data_execution.exception.EntityNotFoundException;
import data_execution.data_execution.service.factory.size.SizeFactory;
import data_execution.data_execution.service.item.ItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CartActionServiceImpl implements CartActionService {
    private final CartService cartService;
    private final ItemService itemService;
    private final SizeFactory sizeFactory;

    public CartActionServiceImpl(CartService cartService, ItemService itemService, SizeFactory sizeFactory) {
        this.cartService = cartService;
        this.itemService = itemService;
        this.sizeFactory = sizeFactory;
    }

    @Override
    public Cart addItem(Long accountId, BuyItemProperties buyItemProperties) {
        Cart cart = cartService.getCartByAccountIdWithResultChecking(accountId);
        Item item = itemService.getByIdWithResultChecking(buyItemProperties.getItemId());
        Size size = sizeFactory.getSizeByEnum(buyItemProperties.getSize());

        List<CartItem> cartsItems = cart.getCartItems();
        Optional<CartItem> itemInTheCart = cartsItems.stream()
                .filter(cartItem -> cartItem.getItem().equals(item) &&
                                    cartItem.getSize().getName().equals(buyItemProperties.getSize()))
                .findAny();
        if (itemInTheCart.isPresent()) {
            itemInTheCart.get().setCount(itemInTheCart.get().getCount() + buyItemProperties.getCount());
        } else {
            cartsItems.add(new CartItem(item, size, buyItemProperties.getCount()));
        }
        cart.calculateCurrentTotalPrice();
        return cartService.update(cart);
    }

    @Override
    public Cart editItem(Long accountId, BuyItemProperties buyItemProperties) {
        Cart cart = cartService.getCartByAccountIdWithResultChecking(accountId);
        Size size = sizeFactory.getSizeByEnum(buyItemProperties.getSize());
        List<CartItem> cartsItems = cart.getCartItems();

        cartsItems.stream()
                .filter(cartItem -> cartItem.getItem().getId() == buyItemProperties.getItemId())
                .forEach(cartItem -> {
                    cartItem.setSize(size);
                    cartItem.setCount(buyItemProperties.getCount());
                });
        cart.calculateCurrentTotalPrice();
        return cartService.update(cart);
    }

    @Override
    public Cart deleteItem(Long accountId, Long cartItemId) {
        Cart cart = cartService.getCartByAccountIdWithResultChecking(accountId);
        List<CartItem> cartsItems = cart.getCartItems();
        System.out.println(cartsItems);

        Optional<CartItem> itemToDelete = cartsItems.stream()
                .filter(cartItem -> cartItem.getId() == cartItemId)
                .findAny();

        if (itemToDelete.isEmpty()) {
            String errorMsg = String.format("CartItem with id=%s not found", cartItemId);
            log.error(errorMsg);
            throw new EntityNotFoundException(errorMsg);
        }
        cartsItems.remove(itemToDelete.get());
        cart.calculateCurrentTotalPrice();
        return cartService.update(cart);
    }

    @Override
    public Cart deleteAllItems(Long accountId) {
        Cart cart = cartService.getCartByAccountIdWithResultChecking(accountId);
        List<CartItem> cartItems = cart.getCartItems();
        cartItems.removeAll(cartItems);
        cart.calculateCurrentTotalPrice();
        return cartService.update(cart);
    }
}

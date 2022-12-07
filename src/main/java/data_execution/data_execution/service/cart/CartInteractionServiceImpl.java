package data_execution.data_execution.service.cart;

import data_execution.data_execution.dto.request.cart.BuyItemProperties;
import data_execution.data_execution.entity.cart.Cart;
import data_execution.data_execution.entity.cart.CartItem;
import data_execution.data_execution.service.factory.size.SizeFactory;
import data_execution.data_execution.service.item.ItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CartInteractionServiceImpl implements CartInteractionService {
    private final CartService cartService;
    private final ItemService itemService;
    private final SizeFactory sizeFactory;

    public CartInteractionServiceImpl(CartService cartService, ItemService itemService, SizeFactory sizeFactory) {
        this.cartService = cartService;
        this.itemService = itemService;
        this.sizeFactory = sizeFactory;
    }

    @Override
    public Cart addItem(BuyItemProperties buyItemProperties, Long accountId) {
        var cart = cartService.getCartByAccountIdWithResultChecking(accountId);
        var item = itemService.getByIdWithResultChecking(buyItemProperties.getItemId());
        var size = sizeFactory.getSizeByEnum(buyItemProperties.getSize());
        cart.addItem(new CartItem(item, size, buyItemProperties.getCount()));
        return cartService.update(cart);
    }

    @Override
    public Cart deleteItem(Long accountId, Long itemId) {
        return null;
    }

    @Override
    public Cart deleteAllItems(Long accountId) {
        return null;
    }
}

package data_execution.data_execution.service.cart;

import data_execution.data_execution.dto.request.cart.BuyItemProperties;
import data_execution.data_execution.entity.cart.Cart;

public interface CartActionService {
    Cart addItem(Long accountId, BuyItemProperties buyItemProperties);
    Cart editItem(Long accountId, BuyItemProperties buyItemProperties);
    Cart deleteItem(Long accountId, Long cartItemId);
    Cart deleteAllItems(Long accountId);
}

package data_execution.data_execution.service.cart;

import data_execution.data_execution.dto.request.cart.BuyItemProperties;
import data_execution.data_execution.entity.cart.Cart;

public interface CartInteractionService {
    Cart addItem(BuyItemProperties buyItemProperties, Long itemId);
    Cart deleteItem(Long accountId, Long itemId);
    Cart deleteAllItems(Long accountId);
}

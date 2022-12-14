package data_execution.data_execution.service.cart;

import data_execution.data_execution.IntegrationTestBase;
import data_execution.data_execution.dto.request.cart.BuyItemProperties;
import data_execution.data_execution.entity.cart.Cart;
import data_execution.data_execution.entity.cart.CartItem;
import data_execution.data_execution.entity.item.Item;
import data_execution.data_execution.entity.item.ItemType;
import data_execution.data_execution.entity.item.SizeEnum;
import data_execution.data_execution.entity.producer.Producer;
import data_execution.data_execution.service.factory.size.SizeEntityFactory;
import data_execution.data_execution.service.item.ItemService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CartActionServiceImplTest extends IntegrationTestBase {
    private static final Long TEST_ID = 1L;
    private Cart testCart;

    @MockBean
    private CartService cartService;
    @MockBean
    private ItemService itemService;

    @Autowired
    private CartActionService cartActionService;
    @Autowired
    private SizeEntityFactory sizeEntityFactory;

    @BeforeEach
    void setUp() {
        var cartItems = new HashSet<>(Set.of(
                CartItem.builder()
                        .item(Item.builder()
                                .title("initial-item-1")
                                .type(ItemType.JACKET)
                                .producer(new Producer())
                                .cost(BigDecimal.valueOf(15))
                                .sizes(new ArrayList<>())
                                .build())
                        .size(sizeEntityFactory.getSizeByEnum(SizeEnum.M))
                        .count(1)
                        .build()));
        testCart = Cart.builder()
                .cartItems(cartItems)
                .currentTotalPrice(BigDecimal.ZERO)
                .build();
        testCart.calculateCurrentTotalPrice();
    }

    @Test
    void addItem() {
        Item additionalItem = Item.builder()
                .title("added-item-1")
                .type(ItemType.JACKET)
                .producer(new Producer())
                .cost(BigDecimal.valueOf(12))
                .sizes(new ArrayList<>())
                .build();
        Cart cartWithAdditionalItem = Cart.builder()
                .cartItems(new HashSet<>(Set.of(CartItem.builder()
                        .item(additionalItem)
                        .size(sizeEntityFactory.getSizeByEnum(SizeEnum.L))
                        .count(1)
                        .build())))
                .currentTotalPrice(BigDecimal.ZERO)
                .build();

        cartWithAdditionalItem.getCartItems().addAll(testCart.getCartItems());
        cartWithAdditionalItem.calculateCurrentTotalPrice();

        when(cartService.getCartByAccountIdWithResultChecking(TEST_ID)).thenReturn(testCart);
        when(itemService.getByIdWithResultChecking(TEST_ID)).thenReturn(additionalItem);
        when(cartService.update(testCart)).thenReturn(cartWithAdditionalItem);

        BuyItemProperties buyItemProperties = BuyItemProperties.builder()
                .itemId(TEST_ID)
                .size(SizeEnum.L)
                .count(1)
                .build();
        Cart result = cartActionService.addItem(TEST_ID, buyItemProperties);

        verify(cartService, times(1)).getCartByAccountIdWithResultChecking(TEST_ID);
        verify(itemService, times(1)).getByIdWithResultChecking(TEST_ID);
        verify(cartService, times(1)).update(cartWithAdditionalItem);

        assertEquals(result, cartWithAdditionalItem);
    }

    @Test
    void editItem() {

    }

    @Test
    void deleteItem() {
    }

    @Test
    void deleteAllItems() {
    }
}
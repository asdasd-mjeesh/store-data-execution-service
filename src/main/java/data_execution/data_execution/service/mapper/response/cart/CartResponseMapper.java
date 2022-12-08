package data_execution.data_execution.service.mapper.response.cart;

import data_execution.data_execution.dto.response.cart.CartResponse;
import data_execution.data_execution.entity.cart.Cart;
import data_execution.data_execution.service.mapper.Mapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartResponseMapper implements Mapper<CartResponse, Cart> {
    private final CartItemResponseMapper cartItemResponseMapper;

    public CartResponseMapper(CartItemResponseMapper cartItemResponseMapper) {
        this.cartItemResponseMapper = cartItemResponseMapper;
    }

    @Override
    public CartResponse map(Cart from) {
        CartResponse cartResponse = CartResponse.builder()
                .cartItems(cartItemResponseMapper.map(from.getCartItems()))
                .currentTotalPrice(from.getCurrentTotalPrice())
                .build();
        cartResponse.setId(from.getId());
        return cartResponse;
    }

    @Override
    public List<CartResponse> map(List<Cart> fromList) {
        return fromList.stream()
                .map(this::map)
                .collect(Collectors.toList());
    }
}

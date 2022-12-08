package data_execution.data_execution.service.mapper.response.cart;

import data_execution.data_execution.dto.response.cart.CartItemResponse;
import data_execution.data_execution.entity.cart.CartItem;
import data_execution.data_execution.service.mapper.Mapper;
import data_execution.data_execution.service.mapper.response.item.ItemResponseMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartItemResponseMapper implements Mapper<CartItemResponse, CartItem> {
    private final ItemResponseMapper itemResponseMapper;

    public CartItemResponseMapper(ItemResponseMapper itemResponseMapper) {
        this.itemResponseMapper = itemResponseMapper;
    }

    @Override
    public CartItemResponse map(CartItem from) {
        CartItemResponse cartItem =  CartItemResponse.builder()
                .itemResponse(itemResponseMapper.map(from.getItem()))
                .size(from.getSize().getName())
                .count(from.getCount())
                .build();
        cartItem.setId(from.getId());
        return cartItem;
    }

    @Override
    public List<CartItemResponse> map(List<CartItem> fromList) {
        return fromList.stream()
                .map(this::map)
                .collect(Collectors.toList());
    }
}

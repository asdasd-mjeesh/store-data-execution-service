package data_execution.data_execution.service.mapper.response.outgoing.order;

import data_execution.data_execution.dto.response.outgoing.order.OrderItemResponse;
import data_execution.data_execution.persistance.entity.order.OrderItem;
import data_execution.data_execution.service.mapper.Mapper;
import data_execution.data_execution.service.mapper.response.outgoing.item.ItemResponseMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderItemResponseMapper implements Mapper<OrderItemResponse, OrderItem> {
    private final ItemResponseMapper itemResponseMapper;

    public OrderItemResponseMapper(ItemResponseMapper itemResponseMapper) {
        this.itemResponseMapper = itemResponseMapper;
    }

    @Override
    public OrderItemResponse map(OrderItem from) {
        return OrderItemResponse.builder()
                .id(from.getId())
                .item(itemResponseMapper.map(from.getItem()))
                .count(from.getCount())
                .size(from.getSize().getName())
                .build();
    }

    @Override
    public List<OrderItemResponse> map(List<OrderItem> fromList) {
        return fromList.stream()
                .map(this::map)
                .collect(Collectors.toList());
    }
}

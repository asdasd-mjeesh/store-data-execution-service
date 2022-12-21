package data_execution.data_execution.service.mapper.response.outgoing.order;

import data_execution.data_execution.dto.response.outgoing.order.OrderResponse;
import data_execution.data_execution.persistance.entity.order.Order;
import data_execution.data_execution.service.mapper.Mapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderResponseMapper implements Mapper<OrderResponse, Order> {
    private final OrderItemResponseMapper orderItemResponseMapper;

    public OrderResponseMapper(OrderItemResponseMapper orderItemResponseMapper) {
        this.orderItemResponseMapper = orderItemResponseMapper;
    }

    @Override
    public OrderResponse map(Order from) {
        return OrderResponse.builder()
                .id(from.getId())
                .orderItems(orderItemResponseMapper.map(from.getOrderItems()))
                .totalPrice(from.getTotalPrice())
                .buyDate(from.getBuyDate())
                .status(from.getStatus())
                .build();
    }

    @Override
    public List<OrderResponse> map(List<Order> fromList) {
        return fromList.stream()
                .map(this::map)
                .collect(Collectors.toList());
    }
}

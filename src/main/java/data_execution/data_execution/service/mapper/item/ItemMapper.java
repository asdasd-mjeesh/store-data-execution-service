package data_execution.data_execution.service.mapper.item;

import data_execution.data_execution.dto.response.item.ItemResponse;
import data_execution.data_execution.entity.item.Item;
import data_execution.data_execution.entity.item.Size;
import data_execution.data_execution.service.mapper.Mapper;
import data_execution.data_execution.service.mapper.producer.ProducerMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemMapper implements Mapper<ItemResponse, Item> {
    private final ProducerMapper producerMapper;

    public ItemMapper(ProducerMapper producerMapper) {
        this.producerMapper = producerMapper;
    }

    @Override
    public ItemResponse map(Item from) {
        return ItemResponse.builder()
                .id(from.getId())
                .title(from.getTitle())
                .type(from.getType())
                .cost(from.getCost())
                .producer(producerMapper.map(from.getProducer()))
                .sizes(from.getSizes().stream()
                        .map(Size::getName)
                        .collect(Collectors.toList()))
                .build();
    }

    @Override
    public List<ItemResponse> map(List<Item> fromList) {
        return fromList.stream()
                .map(this::map)
                .collect(Collectors.toList());
    }
}

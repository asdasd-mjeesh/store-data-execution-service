package data_execution.data_execution.service.mapper.response.outgoing.item;

import data_execution.data_execution.dto.response.outgoing.item.ItemResponse;
import data_execution.data_execution.persistance.entity.item.Item;
import data_execution.data_execution.persistance.entity.item.Size;
import data_execution.data_execution.service.mapper.Mapper;
import data_execution.data_execution.service.mapper.response.outgoing.producer.ProducerResponseMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemResponseMapper implements Mapper<ItemResponse, Item> {
    private final ProducerResponseMapper producerResponseMapper;

    public ItemResponseMapper(ProducerResponseMapper producerResponseMapper) {
        this.producerResponseMapper = producerResponseMapper;
    }

    @Override
    public ItemResponse map(Item entity) {
        return ItemResponse.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .type(entity.getType())
                .cost(entity.getCost())
                .producer(producerResponseMapper.map(entity.getProducer()))
                .sizes(entity.getSizes().stream()
                        .map(Size::getName)
                        .collect(Collectors.toList()))
                .build();
    }

    @Override
    public List<ItemResponse> map(List<Item> entities) {
        return entities.stream()
                .map(this::map)
                .collect(Collectors.toList());
    }
}

package data_execution.data_execution.service.mapper.request.item;

import data_execution.data_execution.dto.request.item.ItemRequest;
import data_execution.data_execution.entity.item.Item;
import data_execution.data_execution.service.factory.size.SizeFactory;
import data_execution.data_execution.service.mapper.Mapper;
import data_execution.data_execution.service.mapper.request.producer.ProducerRequestMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemRequestMapper implements Mapper<Item, ItemRequest> {
    private final ProducerRequestMapper producerRequestMapper;
    private final SizeFactory sizeFactory;

    public ItemRequestMapper(ProducerRequestMapper producerRequestMapper, SizeFactory sizeFactory) {
        this.producerRequestMapper = producerRequestMapper;
        this.sizeFactory = sizeFactory;
    }

    @Override
    public Item map(ItemRequest from) {
        var item = Item.builder()
                .title(from.getTitle())
                .producer(producerRequestMapper.map(from.getProducerRequest()))
                .type(from.getType())
                .cost(from.getCost())
                .sizes(from.getSizes().stream()
                        .map(sizeFactory::getSizeByEnum)
                        .collect(Collectors.toList()))
                .build();

        if (from.getId() != null) {
            item.setId(from.getId());
        }
        return item;
    }

    @Override
    public List<Item> map(List<ItemRequest> fromList) {
        return fromList.stream()
                .map(this::map)
                .collect(Collectors.toList());
    }
}

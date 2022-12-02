package data_execution.data_execution.service.item;

import data_execution.data_execution.entity.item.Item;
import data_execution.data_execution.repository.item.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;

    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public Item save(Item item) {
        return itemRepository.save(item);
    }

    @Override
    public Optional<Item> getById(Long id) {
        return itemRepository.findById(id);
    }

    @Override
    public List<Item> getAll() {
        return itemRepository.findAll();
    }

    @Override
    public boolean update(Item item) {
        var existedItem = itemRepository.findById(item.getId());
        if (existedItem.isPresent()) {
            itemRepository.save(item);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteById(Long id) {
        itemRepository.deleteById(id);
        var item = itemRepository.findById(id);
        return item.isEmpty();
    }
}

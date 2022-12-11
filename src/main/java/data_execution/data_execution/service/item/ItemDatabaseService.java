package data_execution.data_execution.service.item;

import data_execution.data_execution.dto.filter.ItemFilter;
import data_execution.data_execution.entity.item.Item;
import data_execution.data_execution.exception.EntityNotFoundException;
import data_execution.data_execution.repository.item.ItemRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ItemDatabaseService implements ItemService {
    private final ItemRepository itemRepository;

    public ItemDatabaseService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public Item create(Item item) {
        return itemRepository.save(item);
    }

    @Override
    public Optional<Item> getById(Long id) {
        return itemRepository.findById(id);
    }

    @Override
    public Item getByIdWithResultChecking(Long id) {
        var item = itemRepository.findById(id);
        if (item.isEmpty()) {
            String errorMsg = String.format("Item with id=%s now found", id);
            log.error(errorMsg);
            throw new EntityNotFoundException(errorMsg);
        }
        return item.get();
    }

    @Override
    public List<Item> getByFilter(ItemFilter filter) {
        return itemRepository.findByFilter(filter);
    }

    @Override
    public Item update(Item item) {
        return itemRepository.save(item);
    }

    @Override
    public boolean updateWithConfirmation(Item item) {
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

package data_execution.data_execution.service.item;

import data_execution.data_execution.entity.item.Item;

import java.util.List;
import java.util.Optional;

public interface ItemService {
    Item create (Item item);
    Optional<Item> getById(Long id);
    Item getByIdWithResultChecking(Long id);
    List<Item> getAll();
    Item update(Item item);
    boolean updateWithConfirmation(Item item);
    boolean deleteById(Long id);
}

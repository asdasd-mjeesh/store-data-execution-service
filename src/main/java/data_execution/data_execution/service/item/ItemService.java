package data_execution.data_execution.service.item;

import data_execution.data_execution.entity.item.Item;

import java.util.List;
import java.util.Optional;

public interface ItemService {
    Item save (Item item);
    Optional<Item> getById(Long id);
    List<Item> getAll();
    boolean update(Item item);
    boolean deleteById(Long id);
}

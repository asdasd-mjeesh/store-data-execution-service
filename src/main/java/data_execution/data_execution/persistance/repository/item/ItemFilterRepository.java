package data_execution.data_execution.persistance.repository.item;

import data_execution.data_execution.dto.filter.ItemFilter;
import data_execution.data_execution.persistance.entity.item.Item;

import java.util.List;

public interface ItemFilterRepository {
    List<Item> findByFilter(ItemFilter filter);
}

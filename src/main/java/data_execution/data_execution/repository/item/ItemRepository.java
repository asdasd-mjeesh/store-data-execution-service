package data_execution.data_execution.repository.item;

import data_execution.data_execution.entity.item.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long>, ItemFilterRepository {
}

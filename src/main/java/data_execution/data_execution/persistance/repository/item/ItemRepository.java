package data_execution.data_execution.persistance.repository.item;

import data_execution.data_execution.persistance.entity.item.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long>, ItemFilterRepository {
}

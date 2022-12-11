package data_execution.data_execution.repository.item;

import data_execution.data_execution.dto.filter.ItemFilter;
import data_execution.data_execution.entity.item.Item;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public interface ItemFilterRepository {
    Order buildOrder(ItemFilter filter, CriteriaBuilder cb, Root<Item> account);
    Predicate[] buildPredicates(ItemFilter filter, CriteriaBuilder cb, Root<Item> account);
    List<Item> findByFilter(ItemFilter filter);
}

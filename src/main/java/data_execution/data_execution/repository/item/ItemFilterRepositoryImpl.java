package data_execution.data_execution.repository.item;

import data_execution.data_execution.dto.filter.ItemFilter;
import data_execution.data_execution.entity.item.Item;
import data_execution.data_execution.repository.BaseFilterRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class ItemFilterRepositoryImpl
        extends BaseFilterRepository<Item, ItemFilter> implements ItemFilterRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Predicate[] buildPredicates(ItemFilter filter, CriteriaBuilder cb, Root<Item> item) {
        List<Predicate> predicates = new ArrayList<>();
        if (filter.getTitle() != null) {
            predicates.add(cb.like(item.get("title"), filter.getTitle()));
        }
        if (filter.getType() != null) {
            predicates.add(cb.equal(item.get("type"), filter.getType()));
        }
        if (filter.getProducerName() != null) {
            predicates.add(cb.like(item.get("producer").get("name"), filter.getProducerName()));
        }
        if (filter.getMinCost() != null) {
            predicates.add(cb.greaterThanOrEqualTo(item.get("cost"), filter.getMinCost()));
        }
        if (filter.getMaxCost() != null) {
            predicates.add(cb.lessThanOrEqualTo(item.get("cost"), filter.getMaxCost()));
        }
        return predicates.toArray(Predicate[]::new);
    }

    @Override
    public List<Item> findByFilter(ItemFilter filter) {
        var cb = entityManager.getCriteriaBuilder();
        var criteria = cb.createQuery(Item.class);
        var item = criteria.from(Item.class);

        var predicates = this.buildPredicates(filter, cb, item);
        var order = buildOrder(filter, cb, item);

        criteria.select(item)
                .where(predicates)
                .orderBy(order);

        return entityManager.createQuery(criteria)
                .setMaxResults(filter.getLimit())
                .setFirstResult(filter.getOffset())
                .getResultList();
    }
}

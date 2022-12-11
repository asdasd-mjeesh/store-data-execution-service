package data_execution.data_execution.repository.order;

import data_execution.data_execution.dto.filter.OrderFilter;
import data_execution.data_execution.entity.order.Order;
import data_execution.data_execution.repository.BaseFilterRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class OrderFilterRepositoryImpl
        extends BaseFilterRepository<Order, OrderFilter> implements OrderFilterRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    protected Predicate[] buildPredicates(OrderFilter filter, CriteriaBuilder cb, Root<Order> rootEntity) {
        List<Predicate> predicates = new ArrayList<>();
        if (filter.getAccountName() != null) {
            predicates.add(cb.like(rootEntity.get("account").get("name"), filter.getAccountName()));
        }
        if (filter.getMinTotalPrice() != null) {
            predicates.add(cb.greaterThanOrEqualTo(rootEntity.get("totalPrice"), filter.getMinTotalPrice()));
        }
        if (filter.getMaxTotalPrice() != null) {
            predicates.add(cb.lessThanOrEqualTo(rootEntity.get("totalPrice"), filter.getMaxTotalPrice()));
        }
        if (filter.getMinBuyDate() != null) {
            predicates.add(cb.greaterThanOrEqualTo(rootEntity.get("buyDate"), filter.getMinBuyDate()));
        }
        if (filter.getMaxBuyDate() != null) {
            predicates.add(cb.lessThanOrEqualTo(rootEntity.get("buyDate"), filter.getMaxBuyDate()));
        }
        if (filter.getStatus() != null) {
            predicates.add(cb.equal(rootEntity.get("status"), filter.getStatus()));
        }
        return predicates.toArray(Predicate[]::new);
    }

    @Override
    public List<Order> findByFilter(OrderFilter filter) {
        var cb = entityManager.getCriteriaBuilder();
        var criteria = cb.createQuery(Order.class);
        var appOrder = criteria.from(Order.class);

        var predicates = this.buildPredicates(filter, cb, appOrder);
        var order = this.buildOrder(filter, cb, appOrder);

        criteria.select(appOrder)
                .where(predicates)
                .orderBy(order);

        return entityManager.createQuery(criteria)
                .setMaxResults(filter.getLimit())
                .setFirstResult(filter.getOffset())
                .getResultList();
    }
}

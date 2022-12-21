package data_execution.data_execution.persistance.repository.producer;

import data_execution.data_execution.dto.filter.ProducerFilter;
import data_execution.data_execution.persistance.entity.producer.Producer;
import data_execution.data_execution.persistance.repository.BaseFilterRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class ProducerFilterRepositoryImpl
        extends BaseFilterRepository<Producer, ProducerFilter> implements ProducerFilterRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    protected Predicate[] buildPredicates(ProducerFilter filter, CriteriaBuilder cb, Root<Producer> rootEntity) {
        List<Predicate> predicates = new ArrayList<>();
        if (filter.getName() != null) {
            predicates.add(cb.like(rootEntity.get("name"), filter.getName()));
        }
        return predicates.toArray(Predicate[]::new);
    }

    @Override
    public List<Producer> findByFilter(ProducerFilter filter) {
        var cb = entityManager.getCriteriaBuilder();
        var criteria = cb.createQuery(Producer.class);
        var producer = criteria.from(Producer.class);

        var predicates = this.buildPredicates(filter, cb, producer);
        var order = this.buildOrder(filter, cb, producer);

        criteria.select(producer)
                .where(predicates)
                .orderBy(order);

        return entityManager.createQuery(criteria)
                .setMaxResults(filter.getLimit())
                .setFirstResult(filter.getOffset())
                .getResultList();
    }
}

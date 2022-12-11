package data_execution.data_execution.repository;

import data_execution.data_execution.dto.filter.BaseFilter;
import data_execution.data_execution.dto.filter.SortingOrder;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public abstract class BaseFilterRepository<E, F extends BaseFilter> {

    protected Order buildOrder(F filter, CriteriaBuilder cb, Root<E> account) {
        if (filter.getSortingOrder().equals(SortingOrder.ASCENDING)) {
            return cb.asc(account.get(filter.getSortField()));
        }
        return cb.desc(account.get(filter.getSortField()));
    }

    protected abstract Predicate[] buildPredicates(F filter, CriteriaBuilder cb, Root<E> account);
}

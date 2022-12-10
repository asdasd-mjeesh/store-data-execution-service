package data_execution.data_execution.repository.account;

import data_execution.data_execution.dto.filter.AccountFilter;
import data_execution.data_execution.dto.filter.SortingOrder;
import data_execution.data_execution.entity.account.Account;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;

@Repository
public class AccountFilterRepositoryImpl implements AccountFilterRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Account> getByFilter(AccountFilter filter) {
        var cb = entityManager.getCriteriaBuilder();
        var criteria = cb.createQuery(Account.class);
        var account = criteria.from(Account.class);

        List<Predicate> predicates = new ArrayList<>();
        if (filter.getName() != null) {
            predicates.add(cb.like(account.get("name"), filter.getName()));
        }
        if (filter.getContact() != null) {
            predicates.add(cb.like(account.get("contact"), filter.getContact()));
        }
        if (filter.getEmail() != null) {
            predicates.add(cb.like(account.get("email"), filter.getEmail()));
        }
        if (filter.getStatus() != null) {
            predicates.add(cb.like(account.get("status"), filter.getStatus().name()));
        }
        if (filter.getMinimalRegistrationDate() != null) {
            predicates.add(cb.greaterThanOrEqualTo(account.get("registrationDate"),
                    filter.getMinimalRegistrationDate().toString()));
        }
        if (filter.getMaximalRegistrationDate() != null) {
            predicates.add(cb.lessThanOrEqualTo(account.get("registrationDate"),
                    filter.getMaximalRegistrationDate().toString()));
        }

        Order order;
        if (filter.getSortingOrder().equals(SortingOrder.ASCENDING)) {
            order = cb.asc(account.get(filter.getSortField()));
        } else {
            order = cb.desc(account.get(filter.getSortField()));
        }
        criteria.select(account)
                .where(predicates.toArray(Predicate[]::new))
                .orderBy(order);

        return entityManager.createQuery(criteria)
                .setMaxResults(filter.getLimit())
                .setFirstResult(filter.getOffset())
                .getResultList();
    }

    @Override
    public List<Account> getByFilter() {
        var cb = entityManager.getCriteriaBuilder();
        var criteria = cb.createQuery(Account.class);
        var account = criteria.from(Account.class);

        List<Predicate> predicates = new ArrayList<>();
        predicates.add(cb.lessThan(account.get("registrationDate"), LocalDateTime.now()));

        criteria.select(account)
                .where(predicates.toArray(Predicate[]::new))
                .orderBy(cb.asc(account.get("name")));

        return entityManager.createQuery(criteria).getResultList();
    }
}

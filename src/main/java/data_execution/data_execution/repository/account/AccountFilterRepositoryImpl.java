package data_execution.data_execution.repository.account;

import data_execution.data_execution.dto.filter.AccountFilter;
import data_execution.data_execution.entity.account.Account;
import data_execution.data_execution.repository.BaseFilterRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@Repository
public class AccountFilterRepositoryImpl
        extends BaseFilterRepository<Account, AccountFilter> implements AccountFilterRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Predicate[] buildPredicates(AccountFilter filter, CriteriaBuilder cb, Root<Account> rootEntity) {
        List<Predicate> predicates = new ArrayList<>();
        if (filter.getName() != null) {
            predicates.add(cb.like(rootEntity.get("name"), filter.getName()));
        }
        if (filter.getContact() != null) {
            predicates.add(cb.like(rootEntity.get("contact"), filter.getContact()));
        }
        if (filter.getEmail() != null) {
            predicates.add(cb.like(rootEntity.get("email"), filter.getEmail()));
        }
        if (filter.getStatus() != null) {
            predicates.add(cb.equal(rootEntity.get("status"), filter.getStatus()));
        }
        if (filter.getRole() != null) {
            predicates.add(cb.equal(rootEntity.get("role").get("name"), filter.getRole()));
        }
        if (filter.getMinimalRegistrationDate() != null) {
            predicates.add(cb.greaterThanOrEqualTo(rootEntity.get("registrationDate"), filter.getMinimalRegistrationDate()));
        }
        if (filter.getMaximalRegistrationDate() != null) {
            predicates.add(cb.lessThanOrEqualTo(rootEntity.get("registrationDate"), filter.getMaximalRegistrationDate()));
        }
        return predicates.toArray(Predicate[]::new);
    }

    @Override
    public List<Account> findByFilter(AccountFilter filter) {
        var cb = entityManager.getCriteriaBuilder();
        var criteria = cb.createQuery(Account.class);
        var account = criteria.from(Account.class);

        var predicates = this.buildPredicates(filter, cb, account);
        var order = buildOrder(filter, cb, account);

        criteria.select(account)
                .where(predicates)
                .orderBy(order);

        return entityManager.createQuery(criteria)
                .setMaxResults(filter.getLimit())
                .setFirstResult(filter.getOffset())
                .getResultList();
    }
}

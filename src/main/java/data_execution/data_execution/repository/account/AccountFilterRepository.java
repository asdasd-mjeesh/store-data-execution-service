package data_execution.data_execution.repository.account;

import data_execution.data_execution.dto.filter.AccountFilter;
import data_execution.data_execution.entity.account.Account;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public interface AccountFilterRepository {
    Order buildOrder(AccountFilter filter, CriteriaBuilder cb, Root<Account> account);
    Predicate[] buildPredicates(AccountFilter filter, CriteriaBuilder cb, Root<Account> account);
    List<Account> getByFilter(AccountFilter filter);
}

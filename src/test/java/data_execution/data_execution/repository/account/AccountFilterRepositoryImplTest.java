package data_execution.data_execution.repository.account;

import data_execution.data_execution.IntegrationTestBase;
import data_execution.data_execution.dto.filter.AccountFilter;
import data_execution.data_execution.dto.filter.SortingOrder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class AccountFilterRepositoryImplTest extends IntegrationTestBase {
    @Autowired
    private AccountRepository accountRepository;

    @Test
    void getByFilter() {
        var minRegDate = LocalDateTime.of(
                2023, 12, 10, 15, 30, 2);
        var maxRegDate = LocalDateTime.of(
                2021, 12, 10, 15, 2, 2);

        AccountFilter filter = AccountFilter.builder()
                .maximalRegistrationDate(LocalDateTime.now())
//                .minimalRegistrationDate(minRegDate)
                .build();
        filter.setOffset(0);
        filter.setLimit(10);
        filter.setSortingOrder(SortingOrder.ASCENDING);
        filter.setSortField("email");

        var accounts = accountRepository.getByFilter(filter);
        accounts.forEach(System.out::println);
    }
}
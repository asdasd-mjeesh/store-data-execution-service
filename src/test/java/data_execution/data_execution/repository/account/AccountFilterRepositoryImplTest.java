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
//        AccountFilter filter = AccountFilter.builder()
//                .minimalRegistrationDate(
//                        LocalDateTime.of(2022, 12, 10, 15, 30, 2, 2))
//                .build();
//        filter.setOffset(0);
//        filter.setLimit(10);
//        filter.setSortingOrder(SortingOrder.ASCENDING);
//        filter.setSortField("email");

        var account = accountRepository.getByFilter();
        System.out.println(account);
    }
}
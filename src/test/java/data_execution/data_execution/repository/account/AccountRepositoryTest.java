package data_execution.data_execution.repository.account;

import com.fasterxml.jackson.core.JsonProcessingException;
import data_execution.data_execution.TestingEntitiesFactory;
import data_execution.data_execution.entity.account.Account;
import data_execution.data_execution.repository.order.OrderItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@SpringBootTest
class AccountRepositoryTest {
    private static final Long TEST_ID = 1L;
    private Account testAccount;

    @Value("${test.account.save.values}")
    private String accountValue;

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private TestingEntitiesFactory testingEntitiesFactory;

    @BeforeEach
    void setUp() throws JsonProcessingException {
        testAccount = testingEntitiesFactory.getTestAccount();
    }

    @Test
    void saveTest() {
        var saveResult = accountRepository.save(testAccount);
        System.out.println(saveResult);
    }

    @Test
    void findByIdTest() {
        var account = accountRepository.findById(TEST_ID);
        assertTrue(account.isPresent());
    }

    @Test
    void findByEmailTest() {
        var accountByEmail = accountRepository.findByEmail(accountValue);
        assertTrue(accountByEmail.isPresent());
    }

    @Test
    void deleteByIdTest() {
        accountRepository.deleteById(TEST_ID);
        var account = accountRepository.findById(TEST_ID);
        assertTrue(account.isEmpty());
    }
}
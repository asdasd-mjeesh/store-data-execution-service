package data_execution.data_execution.service.account;

import com.fasterxml.jackson.core.JsonProcessingException;
import data_execution.data_execution.IntegrationTestBase;
import data_execution.data_execution.TestingEntitiesFactory;
import data_execution.data_execution.dto.request.account.AccountSaveDto;
import data_execution.data_execution.entity.account.Account;
import data_execution.data_execution.repository.account.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.mockito.Mockito.*;

class AccountDatabaseServiceTest extends IntegrationTestBase {
    private static final Long TEST_ID = 1L;
    private Account testAccount;

    @Value("${test.account.save.values}")
    private String accountEmail;

    @Autowired
    private TestingEntitiesFactory testingEntitiesFactory;
    @MockBean
    private AccountRepository accountRepository;
    @Autowired
    private AccountService accountService;

    @BeforeEach
    void setUp() throws JsonProcessingException {
        testAccount = testingEntitiesFactory.getTestAccount();
    }

    @Test
    void save() {
        var accountSaveDto = AccountSaveDto.builder()
                .name(testAccount.getName())
                .contact(testAccount.getContact())
                .email(testAccount.getEmail())
                .password(testAccount.getPassword())
                .roleName(testAccount.getRole().getName())
                .build();
        accountService.save(accountSaveDto);
        verify(accountRepository, times(1)).save(testAccount);
    }

    @Test
    void getById() {
        accountService.getById(TEST_ID);
        verify(accountRepository, times(1)).findById(TEST_ID);
    }

    @Test
    void getByEmail() {
        accountService.getByEmail(accountEmail);
        verify(accountRepository, times(1)).findByEmail(accountEmail);
    }

    @Test
    void getAll() {
        accountService.getAll();
        verify(accountRepository, times(1)).findAll();
    }

    @Test
    void update() {
        testAccount.setId(TEST_ID);
        when(accountRepository.findById(TEST_ID)).thenReturn(Optional.of(testAccount));
        accountService.update(testAccount);

        verify(accountRepository, times(1)).findById(testAccount.getId());
        verify(accountRepository, times(1)).save(testAccount);
    }
}
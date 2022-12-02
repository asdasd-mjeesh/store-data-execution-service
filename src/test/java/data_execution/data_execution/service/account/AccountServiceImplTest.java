package data_execution.data_execution.service.account;

import data_execution.data_execution.IntegrationTestBase;
import data_execution.data_execution.entity.account.Account;
import data_execution.data_execution.entity.account.Permission;
import data_execution.data_execution.entity.account.Role;
import data_execution.data_execution.entity.account.RoleName;
import data_execution.data_execution.repository.account.AccountRepository;
import data_execution.data_execution.util.DefaultPermissionsFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;
import java.util.stream.Collectors;

import static org.mockito.Mockito.*;

class AccountServiceImplTest extends IntegrationTestBase {
    private static final Long TEST_ID = 1L;
    private Account testAccount;

    @Value("${test.account.save.values}")
    private String accountValue;
    @MockBean
    private AccountRepository accountRepository;
    @Autowired
    private AccountService accountService;

    @BeforeEach
    void setUp() {
        testAccount = Account.builder()
                .name(accountValue)
                .contact(accountValue)
                .email(accountValue)
                .password(accountValue)
                .role(new Role(RoleName.EMPLOYEE,
                        DefaultPermissionsFactory.getEmployeeDefaultPermissions().stream()
                                .map(Permission::new)
                                .collect(Collectors.toSet())))
                .build();
    }

    @Test
    void save() {
        accountService.save(testAccount);
        verify(accountRepository, times(1)).save(testAccount);
    }

    @Test
    void getById() {
        accountService.getById(TEST_ID);
        verify(accountRepository, times(1))
                .findById(TEST_ID);
    }

    @Test
    void getByEmail() {
        accountService.getByEmail(accountValue);
        verify(accountRepository, times(1))
                .findByEmail(accountValue);
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

        verify(accountRepository, times(1))
                .findById(testAccount.getId());
        verify(accountRepository, times(1))
                .save(testAccount);
    }
}
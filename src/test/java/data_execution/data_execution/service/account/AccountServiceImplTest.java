package data_execution.data_execution.service.account;

import data_execution.data_execution.entity.account.Account;
import data_execution.data_execution.entity.account.Permission;
import data_execution.data_execution.entity.account.Role;
import data_execution.data_execution.entity.account.RoleName;
import data_execution.data_execution.util.DefaultPermissionsFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AccountServiceImplTest {
    @Autowired
    private AccountService accountService;

    @Test
    void save() {
        Account account = Account.builder()
                .name("test")
                .contact("test")
                .email("test")
                .password("test")
                .role(new Role(RoleName.EMPLOYEE,
                        DefaultPermissionsFactory.getEmployeeDefaultPermissions().stream()
                                .map(Permission::new)
                                .collect(Collectors.toSet())))
                .build();

        System.out.println(account);
        Account savedAccount = accountService.save(account);
        account.setId(savedAccount.getId());
        assertEquals(account, savedAccount);
    }

    @Test
    void getById() {
    }

    @Test
    void getByEmail() {
    }

    @Test
    void getAll() {
    }

    @Test
    void deleteById() {
    }
}
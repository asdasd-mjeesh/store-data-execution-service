package data_execution.data_execution.service.account;

import data_execution.data_execution.IntegrationTestBase;
import data_execution.data_execution.persistance.entity.account.Account;
import data_execution.data_execution.persistance.entity.account.AccountStatus;
import data_execution.data_execution.persistance.entity.account.PermissionEnum;
import data_execution.data_execution.persistance.entity.account.RoleName;
import data_execution.data_execution.persistance.entity.cart.Cart;
import data_execution.data_execution.service.factory.permissions.ContextPermissionEntityFactory;
import data_execution.data_execution.service.factory.roles.RolesFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AccountRolePermissionsServiceImplTest extends IntegrationTestBase {
    private static final Long TEST_ID = 1L;
    private Account testAccount;

    @MockBean
    private AccountService accountService;
    @MockBean
    private RoleService roleService;

    @Autowired
    private AccountRolePermissionService accountRolePermissionService;
    @Autowired
    private RolesFactory rolesFactory;
    @Autowired
    private ContextPermissionEntityFactory contextPermissionEntityFactory;

    @BeforeEach
    void setUp() {
        testAccount = Account.builder()
                .name("test")
                .contact("test")
                .email("test")
                .password("test")
                .cart(new Cart())
                .role(rolesFactory.getRoleByRoleName(RoleName.USER))
                .registrationDate(LocalDateTime.now())
                .status(AccountStatus.ENABLED)
                .build();
    }

    @Test
    void changeRole() {
        Account accountWithNewRole = Account.builder()
                .name("test")
                .contact("test")
                .email("test")
                .password("test")
                .cart(new Cart())
                .role(rolesFactory.getRoleByRoleName(RoleName.ADMIN))
                .registrationDate(LocalDateTime.now())
                .status(AccountStatus.ENABLED)
                .build();
        when(accountService.getAccountByIdWithResultChecking(TEST_ID)).thenReturn(testAccount);
        when(accountService.update(testAccount)).thenReturn(accountWithNewRole);

        Account result = accountRolePermissionService.changeRole(TEST_ID, RoleName.ADMIN);
        verify(accountService, times(1)).getAccountByIdWithResultChecking(TEST_ID);
        verify(accountService, times(1)).update(testAccount);
        verify(roleService, times(1)).deleteById(testAccount.getRole().getId());

        assertEquals(result, accountWithNewRole);
    }

    @Test
    void addPermissionsToAccount() {
        Account accountWithNewPermissions = Account.builder()
                .name("test")
                .contact("test")
                .email("test")
                .password("test")
                .cart(new Cart())
                .role(rolesFactory.getRoleByRoleName(RoleName.USER))
                .registrationDate(LocalDateTime.now())
                .status(AccountStatus.ENABLED)
                .build();
        accountWithNewPermissions.getRole().getPermissions().add(contextPermissionEntityFactory.getByEnum(PermissionEnum.ITEM_DELETE));

        when(accountService.getAccountByIdWithResultChecking(TEST_ID)).thenReturn(testAccount);
        when(accountService.update(testAccount)).thenReturn(accountWithNewPermissions);

        Account result = accountRolePermissionService.addPermissionsToAccount(TEST_ID, List.of(PermissionEnum.ITEM_DELETE));
        verify(accountService, times(1)).getAccountByIdWithResultChecking(TEST_ID);
        verify(accountService, times(1)).update(testAccount);

        assertEquals(result, accountWithNewPermissions);
    }

    @Test
    void deletePermissions() {
        Account accountWithDeletedPermissions = Account.builder()
                .name("test")
                .contact("test")
                .email("test")
                .password("test")
                .cart(new Cart())
                .role(rolesFactory.getRoleByRoleName(RoleName.USER))
                .registrationDate(LocalDateTime.now())
                .status(AccountStatus.ENABLED)
                .build();
        var newPermissions = accountWithDeletedPermissions.getRole().getPermissions().stream()
                .filter(permission -> !permission.getName().equals(PermissionEnum.CART_EDIT))
                .collect(Collectors.toSet());
        accountWithDeletedPermissions.getRole().setPermissions(newPermissions);

        when(accountService.getAccountByIdWithResultChecking(TEST_ID)).thenReturn(testAccount);
        when(accountService.update(testAccount)).thenReturn(accountWithDeletedPermissions);

        Account result = accountRolePermissionService.deletePermissions(TEST_ID, List.of(PermissionEnum.CART_EDIT));
        verify(accountService, times(1)).getAccountByIdWithResultChecking(TEST_ID);
        verify(accountService, times(1)).update(testAccount);

        assertEquals(result, accountWithDeletedPermissions);
    }
}
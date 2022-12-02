package data_execution.data_execution.service.order;

import data_execution.data_execution.IntegrationTestBase;
import data_execution.data_execution.entity.account.Account;
import data_execution.data_execution.entity.account.Permission;
import data_execution.data_execution.entity.account.Role;
import data_execution.data_execution.entity.account.RoleName;
import data_execution.data_execution.entity.item.Item;
import data_execution.data_execution.entity.item.ItemTypeEnum;
import data_execution.data_execution.entity.item.Size;
import data_execution.data_execution.entity.item.SizeEnum;
import data_execution.data_execution.entity.order.Order;
import data_execution.data_execution.entity.order.OrderItem;
import data_execution.data_execution.entity.order.OrderStatusEnum;
import data_execution.data_execution.entity.producer.Producer;
import data_execution.data_execution.service.account.AccountService;
import data_execution.data_execution.util.DefaultPermissionsFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

class OrderDatabaseServiceTest extends IntegrationTestBase {
    private static final Long TEST_ID = 1L;
    private Order testOrder;

    @Value("${test.order.save.value}")
    private String orderValue;
    @Autowired
    private OrderService orderService;
    @Autowired
    private AccountService accountService;

    @BeforeEach
    void setUp() {
        var testAccount = Account.builder()
                .name(orderValue)
                .contact(orderValue)
                .email(orderValue)
                .password(orderValue)
                .role(new Role(RoleName.EMPLOYEE,
                        DefaultPermissionsFactory.getEmployeeDefaultPermissions().stream()
                                .map(Permission::new)
                                .collect(Collectors.toSet())))
                .build();

        testOrder = Order.builder()
                .account(testAccount)
                .buyDate(LocalDate.now())
                .status(OrderStatusEnum.IN_PROCESS)
                .totalPrice(BigDecimal.valueOf(100.000))
                .build();

        var item = Item.builder()
                .type(ItemTypeEnum.JACKET)
                .title(orderValue)
                .sizes(null)
                .producer(new Producer(orderValue, null, null))
                .bigDecimal(BigDecimal.valueOf(100.000))
                .build();
        var orderItems = List.of(new OrderItem(
                testOrder, item, new Size(SizeEnum.S45), 1
        ));

        testOrder.setOrderItems(orderItems);
    }

    @Test
    void save() {
        accountService.save(testOrder.getAccount());
        testOrder.getAccount().setId(TEST_ID);
        orderService.save(testOrder);
        System.out.println(testOrder);
    }

    @Test
    void getById() {
    }

    @Test
    void getAll() {
    }

    @Test
    void update() {
    }
}
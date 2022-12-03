package data_execution.data_execution;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
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
import data_execution.data_execution.entity.producer.Contact;
import data_execution.data_execution.entity.producer.Email;
import data_execution.data_execution.entity.producer.Producer;
import data_execution.data_execution.util.DefaultPermissionsFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TestingEntitiesFactory {
    private static final ObjectMapper objectMapper = new ObjectMapper()
            .registerModule(new JavaTimeModule());
    private final Long TEST_ID = 1L;

    @Value("${test.account.save.values}")
    private String accountValue = "account-test";
    private Account testAccount;

    @Value("${test.producer.save.values}")
    private String producerValue = "producer-test";
    private Producer testProducer;

    @Value("${test.item.save.values}")
    private String itemValue = "item-test";
    @Value("${test.item.save.cost}")
    private BigDecimal itemCost = BigDecimal.valueOf(100.000);
    private Item testItem;

    private Order testOrder;

    {
        try {
            accountInit();
            producerInit();
            itemInit();
            orderInit();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    private void accountInit() {
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

    private void producerInit() {
        var contacts = Set.of(
                new Contact(producerValue)
        );
        var emails = Set.of(
                new Email(producerValue)
        );
        testProducer = Producer.builder()
                .name(producerValue)
                .contacts(contacts)
                .emails(emails)
                .build();
    }

    private void itemInit() throws JsonProcessingException {
        var testProducer = this.getTestProducer();
        testProducer.setId(TEST_ID);

        SizeEnum[] sizeEnums = SizeEnum.values();
        List<Size> sizes = Arrays.stream(sizeEnums)
                .map(Size::new)
                .collect(Collectors.toList());

        testItem = Item.builder()
                .bigDecimal(itemCost)
                .producer(testProducer)
                .sizes(sizes)
                .title(itemValue)
                .type(ItemTypeEnum.JACKET)
                .build();
    }

    private void orderInit() throws JsonProcessingException {
        var account = this.getTestAccount();
        account.setId(TEST_ID);

        testOrder = Order.builder()
                .account(account)
                .buyDate(LocalDate.now())
                .status(OrderStatusEnum.IN_PROCESS)
                .totalPrice(BigDecimal.valueOf(100.000))
                .build();

        var item = this.getTestItem();
        item.setId(TEST_ID);

        var orderItems = List.of(
                new OrderItem(testOrder, item, new Size(SizeEnum.S45), 1));
        testOrder.setOrderItems(orderItems);
    }

    public Account getTestAccount() throws JsonProcessingException {
        return objectMapper.readValue(objectMapper.writeValueAsString(testAccount), Account.class);
    }

    public Producer getTestProducer() throws JsonProcessingException {
        return objectMapper.readValue(objectMapper.writeValueAsString(testProducer), Producer.class);
    }

    public Item getTestItem() throws JsonProcessingException {
        return objectMapper.readValue(objectMapper.writeValueAsString(testItem), Item.class);
    }

    public Order getTestOrder() throws JsonProcessingException {
        return objectMapper.readValue(objectMapper.writeValueAsString(testOrder), Order.class);
    }
}

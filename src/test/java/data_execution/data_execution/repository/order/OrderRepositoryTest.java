package data_execution.data_execution.repository.order;

import com.fasterxml.jackson.core.JsonProcessingException;
import data_execution.data_execution.TestingEntitiesFactory;
import data_execution.data_execution.entity.order.Order;
import data_execution.data_execution.entity.order.OrderItem;
import data_execution.data_execution.repository.account.AccountRepository;
import data_execution.data_execution.repository.item.ItemRepository;
import data_execution.data_execution.repository.producer.ProducerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@SpringBootTest
class OrderRepositoryTest {
    private static final Long TEST_ID = 1L;
    private Order testOrder;

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private ProducerRepository producerRepository;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private TestingEntitiesFactory testingEntitiesFactory;

    @BeforeEach
    void setUp() throws JsonProcessingException {
        this.testOrder = testingEntitiesFactory.getTestOrder();
    }

    @Test
    void saveTest() {
        var account = accountRepository.save(testOrder.getAccount());
        testOrder.setAccount(account);

        var orderItems = testOrder.getOrderItems();
        System.out.println(orderItems);
        orderItems.get(0).getItem().setId(1L);

        orderItems.forEach(orderItem -> producerRepository.save(orderItem.getItem().getProducer()));

        var items = orderItems.stream()
                .map(OrderItem::getItem)
                .collect(Collectors.toList());

        itemRepository.saveAll(items);
        orderItems.get(0).setItem(items.get(0));

        orderItemRepository.saveAll(orderItems);

        var order = orderRepository.save(testOrder);
        System.out.println(order);
    }
}
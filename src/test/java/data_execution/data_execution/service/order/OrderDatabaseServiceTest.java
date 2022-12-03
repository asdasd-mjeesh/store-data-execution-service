package data_execution.data_execution.service.order;

import com.fasterxml.jackson.core.JsonProcessingException;
import data_execution.data_execution.IntegrationTestBase;
import data_execution.data_execution.TestingEntitiesFactory;
import data_execution.data_execution.entity.order.Order;
import data_execution.data_execution.repository.order.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrderDatabaseServiceTest extends IntegrationTestBase {
    private static final Long TEST_ID = 1L;
    private Order testOrder;

    @Autowired
    private TestingEntitiesFactory testingEntitiesFactory;
    @MockBean
    private OrderRepository orderRepository;
    @Autowired
    private OrderService orderService;

    @BeforeEach
    void setUp() throws JsonProcessingException {
        testOrder = testingEntitiesFactory.getTestOrder();
    }

    @Test
    void save() {
        orderService.save(testOrder);
        verify(orderRepository, times(1)).save(testOrder);
    }

    @Test
    void getById() {
        orderService.getById(TEST_ID);
        verify(orderRepository, times(1)).findById(TEST_ID);
    }

    @Test
    void getAll() {
        orderService.getAll();
        verify(orderRepository, times(1)).findAll();
    }

    @Test
    void update() {
        testOrder.setId(TEST_ID);
        when(orderRepository.findById(TEST_ID)).thenReturn(Optional.of(testOrder));

        boolean isUpdated = orderService.update(testOrder);
        verify(orderRepository, times(1)).findById(TEST_ID);
        verify(orderRepository, times(1)).save(testOrder);
        assertTrue(isUpdated);
    }
}
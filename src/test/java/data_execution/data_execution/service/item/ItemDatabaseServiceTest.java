package data_execution.data_execution.service.item;

import com.fasterxml.jackson.core.JsonProcessingException;
import data_execution.data_execution.IntegrationTestBase;
import data_execution.data_execution.TestingEntitiesFactory;
import data_execution.data_execution.entity.item.Item;
import data_execution.data_execution.repository.item.ItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.mockito.Mockito.*;

class ItemDatabaseServiceTest extends IntegrationTestBase {
    private static final Long TEST_ID = 1L;
    private Item testItem;

    @Autowired
    private TestingEntitiesFactory testingEntitiesFactory;
    @MockBean
    private ItemRepository itemRepository;
    @Autowired
    private ItemService itemService;

    @BeforeEach
    void setUp() throws JsonProcessingException {
        testItem = testingEntitiesFactory.getTestItem();
    }

    @Test
    void save() {
        itemService.create(testItem);
        verify(itemRepository, times(1)).save(testItem);
    }

    @Test
    void getById() {
        itemService.getById(TEST_ID);
        verify(itemRepository, times(1)).findById(TEST_ID);
    }

    @Test
    void update() {
        testItem.setId(TEST_ID);
        when(itemRepository.findById(TEST_ID)).thenReturn(Optional.of(testItem));
        itemService.update(testItem);

        verify(itemRepository, times(1)).findById(TEST_ID);
        verify(itemRepository, times(1)).save(testItem);
    }
}
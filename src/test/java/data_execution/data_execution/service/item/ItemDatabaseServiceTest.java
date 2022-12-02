package data_execution.data_execution.service.item;

import data_execution.data_execution.IntegrationTestBase;
import data_execution.data_execution.entity.item.Item;
import data_execution.data_execution.entity.item.ItemTypeEnum;
import data_execution.data_execution.entity.item.Size;
import data_execution.data_execution.entity.item.SizeEnum;
import data_execution.data_execution.entity.producer.Contact;
import data_execution.data_execution.entity.producer.Email;
import data_execution.data_execution.entity.producer.Producer;
import data_execution.data_execution.repository.item.ItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static org.mockito.Mockito.*;

class ItemDatabaseServiceTest extends IntegrationTestBase {
    private static final Long TEST_ID = 1L;
    private Item testItem;

    @Value("${test.item.save.values}")
    private String itemValue;
    @Value("${test.item.save.cost}")
    private BigDecimal itemCost;
    @MockBean
    private ItemRepository itemRepository;
    @Autowired
    private ItemService itemService;

    @BeforeEach
    void setUp() {
        var contacts = Set.of(
                new Contact(itemValue)
        );
        var emails = Set.of(
                new Email(itemValue)
        );
        var testProducer = Producer.builder()
                .name(itemValue)
                .contacts(contacts)
                .emails(emails)
                .build();
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

    @Test
    void save() {
        itemService.save(testItem);
        verify(itemRepository, times(1))
                .save(testItem);
    }

    @Test
    void getById() {
        itemService.getById(TEST_ID);
        verify(itemRepository, times(1))
                .findById(TEST_ID);
    }

    @Test
    void getAll() {
        itemService.getAll();
        verify(itemRepository, times(1))
                .findAll();
    }

    @Test
    void update() {
        testItem.setId(TEST_ID);
        when(itemRepository.findById(TEST_ID)).thenReturn(Optional.of(testItem));
        itemService.update(testItem);

        verify(itemRepository, times(1))
                .findById(TEST_ID);
        verify(itemRepository, times(1))
                .save(testItem);
    }
}
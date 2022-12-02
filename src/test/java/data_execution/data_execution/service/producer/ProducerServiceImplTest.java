package data_execution.data_execution.service.producer;

import data_execution.data_execution.IntegrationTestBase;
import data_execution.data_execution.entity.producer.Contact;
import data_execution.data_execution.entity.producer.Email;
import data_execution.data_execution.entity.producer.Producer;
import data_execution.data_execution.repository.producer.ProducerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;
import java.util.Set;

import static org.mockito.Mockito.*;

class ProducerServiceImplTest extends IntegrationTestBase {
    private static final Long TEST_ID = 1L;
    private Producer testProducer;

    @Value("${test.producer.save.values}")
    private String producerValue;
    @MockBean
    private ProducerRepository producerRepository;
    @Autowired
    private ProducerService producerService;

    @BeforeEach
    void setUp() {
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
        testProducer.setId(TEST_ID);
    }

    @Test
    void save() {
        producerService.save(testProducer);
        verify(producerRepository, times(1))
                .save(testProducer);
    }

    @Test
    void getById() {
        producerService.getById(TEST_ID);
        verify(producerRepository, times(1))
                .findById(TEST_ID);
    }

    @Test
    void getAll() {
        producerService.getAll();
        verify(producerRepository, times(1))
                .findAll();
    }

    @Test
    void update() {
        when(producerRepository.findById(TEST_ID)).thenReturn(Optional.of(testProducer));
        producerService.update(testProducer);

        verify(producerRepository, times(1))
                .findById(TEST_ID);
        verify(producerRepository, times(1))
                .save(testProducer);
    }
}
package data_execution.data_execution.service.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import data_execution.data_execution.IntegrationTestBase;
import data_execution.data_execution.TestingEntitiesFactory;
import data_execution.data_execution.entity.producer.Producer;
import data_execution.data_execution.repository.producer.ProducerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.mockito.Mockito.*;

class ProducerDatabaseServiceTest extends IntegrationTestBase {
    private static final Long TEST_ID = 1L;
    private Producer testProducer;

    @Autowired
    private TestingEntitiesFactory testingEntitiesFactory;
    @MockBean
    private ProducerRepository producerRepository;
    @Autowired
    private ProducerService producerService;

    @BeforeEach
    void setUp() throws JsonProcessingException {
        testProducer = testingEntitiesFactory.getTestProducer();
    }

    @Test
    void save() {
        producerService.create(testProducer);
        verify(producerRepository, times(1)).save(testProducer);
    }

    @Test
    void getById() {
        producerService.getById(TEST_ID);
        verify(producerRepository, times(1)).findById(TEST_ID);
    }

    @Test
    void getAll() {
        producerService.getAll();
        verify(producerRepository, times(1)).findAll();
    }

    @Test
    void update() {
        testProducer.setId(TEST_ID);
        when(producerRepository.findById(TEST_ID)).thenReturn(Optional.of(testProducer));
        producerService.update(testProducer);

        verify(producerRepository, times(1)).findById(TEST_ID);
        verify(producerRepository, times(1)).save(testProducer);
    }
}
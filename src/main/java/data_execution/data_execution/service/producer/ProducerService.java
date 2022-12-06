package data_execution.data_execution.service.producer;

import data_execution.data_execution.entity.producer.Producer;

import java.util.List;
import java.util.Optional;

public interface ProducerService {
    Producer create(Producer producer);
    Optional<Producer> getById(Long id);
    List<Producer> getAll();
    boolean update(Producer producer);
    boolean deleteById(Long id);
}

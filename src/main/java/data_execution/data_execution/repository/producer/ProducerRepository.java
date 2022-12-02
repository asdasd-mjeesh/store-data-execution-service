package data_execution.data_execution.repository.producer;

import data_execution.data_execution.entity.producer.Producer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProducerRepository extends JpaRepository<Producer, Long> {
}

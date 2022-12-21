package data_execution.data_execution.persistance.repository.producer;

import data_execution.data_execution.dto.filter.ProducerFilter;
import data_execution.data_execution.persistance.entity.producer.Producer;

import java.util.List;

public interface ProducerFilterRepository {
    List<Producer> findByFilter(ProducerFilter filter);
}

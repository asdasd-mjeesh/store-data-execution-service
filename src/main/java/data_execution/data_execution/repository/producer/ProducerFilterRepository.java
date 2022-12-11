package data_execution.data_execution.repository.producer;

import data_execution.data_execution.dto.filter.ProducerFilter;
import data_execution.data_execution.entity.producer.Producer;

import java.util.List;

public interface ProducerFilterRepository {
    List<Producer> findByFilter(ProducerFilter filter);
}

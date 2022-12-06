package data_execution.data_execution.service.producer;

import data_execution.data_execution.entity.producer.Producer;
import data_execution.data_execution.exception.EntityNotFoundException;
import data_execution.data_execution.repository.producer.ProducerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProducerDatabaseService implements ProducerService {
    private final ProducerRepository producerRepository;

    public ProducerDatabaseService(ProducerRepository producerRepository) {
        this.producerRepository = producerRepository;
    }

    @Override
    public Producer create(Producer producer) {
        return producerRepository.save(producer);
    }

    @Override
    public Optional<Producer> getById(Long id) {
        return producerRepository.findById(id);
    }

    @Override
    public List<Producer> getAll() {
        return producerRepository.findAll();
    }

    @Override
    public Producer update(Producer producer) {
        var existedProducer = producerRepository.findById(producer.getId());
        if (existedProducer.isPresent()) {
            return producerRepository.save(producer);
        }
        throw new EntityNotFoundException(String.format("Producer with id=%s not found", producer.getId()));
    }

    @Override
    public boolean deleteById(Long id) {
        producerRepository.deleteById(id);
        var producer = producerRepository.findById(id);
        return producer.isEmpty();
    }
}

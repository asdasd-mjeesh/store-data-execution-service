package data_execution.data_execution.service.producer;

import data_execution.data_execution.entity.producer.Producer;
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
    public Producer save(Producer producer) {
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
    public boolean update(Producer producer) {
        var existedProducer = producerRepository.findById(producer.getId());
        if (existedProducer.isPresent()) {
            producerRepository.save(producer);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteById(Long id) {
        producerRepository.deleteById(id);
        var producer = producerRepository.findById(id);
        return producer.isEmpty();
    }
}

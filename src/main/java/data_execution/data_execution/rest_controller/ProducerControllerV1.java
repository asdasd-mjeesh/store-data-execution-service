package data_execution.data_execution.rest_controller;

import data_execution.data_execution.dto.response.producer.ProducerDto;
import data_execution.data_execution.entity.producer.Producer;
import data_execution.data_execution.exception.EntityNotFoundException;
import data_execution.data_execution.service.mapper.producer.ProducerMapper;
import data_execution.data_execution.service.producer.ProducerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/producers")
public class ProducerControllerV1 {
    private final ProducerService producerService;
    private final ProducerMapper producerMapper;

    public ProducerControllerV1(ProducerService producerService, ProducerMapper producerMapper) {
        this.producerService = producerService;
        this.producerMapper = producerMapper;
    }

    @PostMapping("/")
    public ResponseEntity<ProducerDto> createProducer(@RequestBody Producer producer) {
        var savedProducer = producerService.create(producer);
        var producerDto = producerMapper.map(savedProducer);
        return ResponseEntity.ok(producerDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProducerDto> getProducerById(@PathVariable(name = "id") Long id) {
        var producer = producerService.getById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("Producer with id=%s not found", id)
                ));
        var producerDto = producerMapper.map(producer);
        return ResponseEntity.ok(producerDto);
    }

    @PatchMapping("/")
    public ResponseEntity<ProducerDto> update(@RequestBody Producer producer) {
        var updatedProducer = producerService.update(producer);
        var updatedProducerDto = producerMapper.map(updatedProducer);
        return ResponseEntity.ok(updatedProducerDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable(name = "id") Long id) {
        boolean isDeleted = producerService.deleteById(id);
        if (isDeleted) {
            return ResponseEntity.ok("Deleted successfully");
        }
        return new ResponseEntity<>("Delete was failed", HttpStatus.CONFLICT);
    }
}

package data_execution.data_execution.rest_controller;

import data_execution.data_execution.dto.filter.ProducerFilter;
import data_execution.data_execution.dto.request.producer.ProducerRequest;
import data_execution.data_execution.dto.response.producer.ProducerResponse;
import data_execution.data_execution.entity.producer.Producer;
import data_execution.data_execution.exception.EntityNotFoundException;
import data_execution.data_execution.service.mapper.request.producer.ProducerRequestMapper;
import data_execution.data_execution.service.mapper.response.producer.ProducerResponseMapper;
import data_execution.data_execution.service.producer.ProducerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/producers")
public class ProducerControllerV1 {
    private final ProducerService producerService;
    private final ProducerRequestMapper producerRequestMapper;
    private final ProducerResponseMapper producerResponseMapper;

    public ProducerControllerV1(ProducerService producerService,
                                ProducerRequestMapper producerRequestMapper, ProducerResponseMapper producerResponseMapper) {
        this.producerService = producerService;
        this.producerRequestMapper = producerRequestMapper;
        this.producerResponseMapper = producerResponseMapper;
    }

    @PostMapping("/")
    public ResponseEntity<ProducerResponse> createProducer(@RequestBody ProducerRequest producerRequest) {
        var producer = producerRequestMapper.map(producerRequest);
        producer = producerService.create(producer);
        var producerResponse = producerResponseMapper.map(producer);
        return ResponseEntity.ok(producerResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProducerResponse> getProducerById(@PathVariable(name = "id") Long id) {
        var producer = producerService.getById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Producer with id=%s not found", id)));
        var producerDto = producerResponseMapper.map(producer);
        return ResponseEntity.ok(producerDto);
    }

    @GetMapping("/filter")
    public ResponseEntity<List<ProducerResponse>> getByFilter(@RequestBody ProducerFilter filter) {
        var producers = producerService.getByFilter(filter);
        var producersResponse = producerResponseMapper.map(producers);
        return ResponseEntity.ok(producersResponse);
    }

    @PatchMapping("/")
    public ResponseEntity<ProducerResponse> update(@RequestBody Producer producer) {
        var updatedProducer = producerService.update(producer);
        var updatedProducerResponse = producerResponseMapper.map(updatedProducer);
        return ResponseEntity.ok(updatedProducerResponse);
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

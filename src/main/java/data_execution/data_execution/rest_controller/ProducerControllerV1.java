package data_execution.data_execution.rest_controller;

import data_execution.data_execution.dto.response.producer.ProducerDto;
import data_execution.data_execution.entity.producer.Producer;
import data_execution.data_execution.service.mapper.producer.ProducerMapper;
import data_execution.data_execution.service.producer.ProducerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}

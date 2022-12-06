package data_execution.data_execution.rest_controller;

import data_execution.data_execution.service.producer.ProducerService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/producers")
public class ProducerControllerV1 {
    private ProducerService producerService;
//
//    @PostMapping("/")
//    public
}

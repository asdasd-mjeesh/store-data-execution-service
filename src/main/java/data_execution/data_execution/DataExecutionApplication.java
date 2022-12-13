package data_execution.data_execution;

import data_execution.data_execution.service.factory.ContextInitService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DataExecutionApplication {

    public static void main(String[] args) {
        SpringApplication.run(DataExecutionApplication.class, args);
    }
}

package data_execution.data_execution.config;

import data_execution.data_execution.service.factory.ContextInitService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EnvirenmentInitConfig {

    @Bean
    CommandLineRunner synchronizePermissions(@Qualifier("contextPermissionEntityFactory") ContextInitService contextInitService) {
        return args -> {
            contextInitService.init();
        };
    }

    @Bean
    CommandLineRunner synchronizeSizes(@Qualifier("sizeEntityFactory") ContextInitService contextInitService) {
        return args -> {
            contextInitService.init();
        };
    }
}

package data_execution.data_execution;

import data_execution.data_execution.entity.item.Size;
import data_execution.data_execution.entity.item.SizeEnum;
import data_execution.data_execution.service.factory.permissions.PermissionInitService;
import data_execution.data_execution.service.item.SizeService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.stream.Collectors;

@SpringBootApplication
public class DataExecutionApplication {

    public static void main(String[] args) {
        SpringApplication.run(DataExecutionApplication.class, args);
    }

    @Bean
    CommandLineRunner createPermissions(PermissionInitService permissionInitService) {
        return args -> {
            permissionInitService.init();
        };
    }

    @Bean
    CommandLineRunner createSizes(SizeService sizeService) {
        return args -> {
            var sizes = Arrays.stream(SizeEnum.values())
                    .map(Size::new)
                    .collect(Collectors.toList());
//            sizes.forEach(sizeService::create);
        };
    }
}

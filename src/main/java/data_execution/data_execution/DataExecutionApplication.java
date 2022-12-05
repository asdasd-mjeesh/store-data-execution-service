package data_execution.data_execution;

import data_execution.data_execution.entity.account.Permission;
import data_execution.data_execution.entity.account.PermissionEnum;
import data_execution.data_execution.entity.item.Size;
import data_execution.data_execution.entity.item.SizeEnum;
import data_execution.data_execution.service.account.PermissionService;
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
    CommandLineRunner createPermissions(PermissionService permissionService) {
        return args -> {
            var permissions = Arrays.stream(PermissionEnum.values())
                    .map(Permission::new)
                    .collect(Collectors.toList());
//            permissions.forEach(permissionService::create);
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

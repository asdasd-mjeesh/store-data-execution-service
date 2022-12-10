package data_execution.data_execution.dto.response.account;

import data_execution.data_execution.entity.account.AccountStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class AccountResponse {
    private Long id;
    private String name;
    private String contact;
    private String email;
    private String password;
    private RoleResponse role;
    private LocalDateTime registrationDate;
    private AccountStatus status;
}

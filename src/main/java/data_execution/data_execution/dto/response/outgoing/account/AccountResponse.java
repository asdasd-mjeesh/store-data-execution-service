package data_execution.data_execution.dto.response.outgoing.account;

import data_execution.data_execution.persistance.entity.account.AccountStatus;
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

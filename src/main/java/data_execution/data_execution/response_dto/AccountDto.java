package data_execution.data_execution.response_dto;

import data_execution.data_execution.entity.account.Role;
import lombok.Data;

@Data
public class AccountDto {
    private Long id;
    private String name;
    private String contact;
    private String email;
    private String password;
    private Role role;
}

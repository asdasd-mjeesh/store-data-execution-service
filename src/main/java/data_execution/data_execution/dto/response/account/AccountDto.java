package data_execution.data_execution.dto.response.account;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AccountDto {
    private Long id;
    private String name;
    private String contact;
    private String email;
    private String password;
    private RoleDto role;
}

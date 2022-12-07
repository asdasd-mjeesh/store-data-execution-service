package data_execution.data_execution.dto.request.account;

import data_execution.data_execution.entity.account.RoleName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountRequest {
    private Long id;
    private String name;
    private String contact;
    private String email;
    private String password;
    private RoleName roleName;
}

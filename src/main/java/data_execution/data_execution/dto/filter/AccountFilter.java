package data_execution.data_execution.dto.filter;

import data_execution.data_execution.persistance.entity.account.AccountStatus;
import data_execution.data_execution.persistance.entity.account.RoleName;
import lombok.*;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountFilter extends BaseFilter {
    private String name;
    private String contact;
    private String email;
    private LocalDateTime minimalRegistrationDate;
    private LocalDateTime maximalRegistrationDate;
    private AccountStatus status;
    private RoleName role;
}

package data_execution.data_execution.entity.account;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "permission", schema = "store_domain")
public class Permission extends BaseEntity {
    @Column(name = "name")
    @Enumerated(value = EnumType.STRING)
    private PermissionEnum name;
}

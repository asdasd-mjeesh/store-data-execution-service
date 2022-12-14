package data_execution.data_execution.persistance.entity.account;

import data_execution.data_execution.persistance.entity.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Builder
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(schema = "store_domain", name = "role")
public class Role extends BaseEntity {
    @Enumerated(value = EnumType.STRING)
    private RoleName name;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(schema = "store_domain", name = "role_permission",
        joinColumns =
        @JoinColumn(name = "role_id", referencedColumnName = "id"),
        inverseJoinColumns =
        @JoinColumn(name = "permission_id", referencedColumnName = "id"))
    private Set<Permission> permissions = new HashSet<>();
}

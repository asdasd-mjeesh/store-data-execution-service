package data_execution.data_execution.entity.account;

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
@Table(name = "role", schema = "store_domain")
public class Role extends BaseEntity {
    @Enumerated(value = EnumType.STRING)
    private RoleName name;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(schema = "store_domain", name = "role_permission",
        joinColumns =
        @JoinColumn(name = "role_id", referencedColumnName = "id"),
        inverseJoinColumns =
        @JoinColumn(name = "permission_id", referencedColumnName = "id"))
    private Set<Permission> permissions = new HashSet<>();
}

package data_execution.data_execution.persistance.entity.account;

import data_execution.data_execution.persistance.entity.BaseEntity;
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
@Table(schema = "store_domain", name = "permission")
public class Permission extends BaseEntity {
    @Column(name = "name")
    @Enumerated(value = EnumType.STRING)
    private PermissionEnum name;
}

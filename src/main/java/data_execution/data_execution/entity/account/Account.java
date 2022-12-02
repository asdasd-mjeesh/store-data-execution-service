package data_execution.data_execution.entity.account;

import data_execution.data_execution.entity.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Builder
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(schema = "store_domain", name = "account")
public class Account extends BaseEntity {
    @Column(name = "name")
    private String name;
    @Column(name = "contact")
    private String contact;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @ManyToOne(cascade = CascadeType.ALL)
    private Role role;
}

package data_execution.data_execution.entity.producer;

import data_execution.data_execution.entity.BaseEntity;
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
@Table(schema = "store_domain", name = "producer")
public class Producer extends BaseEntity {
    @Column(name = "name")
    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Email> emails = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Contact> contacts = new HashSet<>();
}

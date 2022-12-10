package data_execution.data_execution.entity.producer;

import data_execution.data_execution.entity.BaseEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Builder
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(schema = "store_domain", name = "email")
public class Email extends BaseEntity {
    @Column(name = "address")
    private String address;
}

package data_execution.data_execution.persistance.entity.producer;

import data_execution.data_execution.persistance.entity.BaseEntity;
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
@Table(schema = "store_domain", name = "phone")
public class Contact extends BaseEntity {
    @Column(name = "phone_number")
    private String phoneNumber;
}

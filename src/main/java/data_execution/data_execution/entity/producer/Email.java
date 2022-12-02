package data_execution.data_execution.entity.producer;

import data_execution.data_execution.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(schema = "store_domain", name = "producers_email")
public class Email extends BaseEntity {
    @Column(name = "address")
    private String address;
}

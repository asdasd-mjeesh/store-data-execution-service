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
@Table(schema = "store_domain", name = "producers_phone")
public class Contact extends BaseEntity {
    @Column(name = "phone_number")
    private String phoneNumber;
}

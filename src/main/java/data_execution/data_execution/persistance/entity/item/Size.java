package data_execution.data_execution.persistance.entity.item;

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
@Table(schema = "store_domain", name = "size")
public class Size extends BaseEntity {
    @Column(name = "name")
    @Enumerated(value = EnumType.STRING)
    private SizeEnum name;
    @Column(name = "type")
    @Enumerated(value = EnumType.STRING)
    private SizeType type;

    public Size(SizeEnum name) {
        this.name = name;
        this.type = name.getType();
    }
}

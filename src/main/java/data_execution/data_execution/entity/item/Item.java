package data_execution.data_execution.entity.item;

import data_execution.data_execution.entity.BaseEntity;
import data_execution.data_execution.entity.producer.Producer;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Builder
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(schema = "store_domain", name = "item")
public class Item extends BaseEntity {
    @Column(name = "title")
    private String title;
    @Column(name = "type")
    @Enumerated(value = EnumType.STRING)
    private ItemTypeEnum type;
    @Column(name = "cost")
    private BigDecimal bigDecimal;

    @ManyToOne
    @JoinColumn(name = "producer_id", referencedColumnName = "id")
    private Producer producer;

    @ManyToMany
    @JoinTable(schema = "store_domain", name = "item_size",
            joinColumns =
            @JoinColumn(name = "item_id", referencedColumnName = "id"),
            inverseJoinColumns =
            @JoinColumn(name = "size_id", referencedColumnName = "id"))
    private List<Size> sizes;
}

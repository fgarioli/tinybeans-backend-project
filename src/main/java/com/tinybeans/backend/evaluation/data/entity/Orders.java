package com.tinybeans.backend.evaluation.data.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author danrodrigues
 *         Date: 13/5/22
 */
@Entity
@Data
@ToString(exclude = { "paymentId" })
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Orders extends BaseEntity {

    @ManyToMany
    private List<Item> items;

    @Column(scale = 2, precision = 5)
    private BigDecimal subTotal, discount, finalPrice;

    private String paymentId;

    public Orders(List<Item> items) {
        this.items = items;
        this.subTotal = BigDecimal.valueOf(0);
        for (Item item : items) {
            this.subTotal = this.subTotal.add(BigDecimal.valueOf(item.getPrice()));
        }
        this.discount = BigDecimal.valueOf(0);
        this.finalPrice = this.subTotal.subtract(this.discount);
    }

}

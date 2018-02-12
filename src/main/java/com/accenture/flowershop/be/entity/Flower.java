package com.accenture.flowershop.be.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name="Flowers")
@NamedQueries
({
        @NamedQuery(name = "getAllFlowers", query = "Select f from Flower f"),
        @NamedQuery(name = "getFlowersWithPriceBounds", query = "Select f from Flower f where f.price >= :from and f.price <= :to"),
        @NamedQuery(name = "getFlowersByName", query = "Select f from Flower f where LOWER(f.name) like :substring")
})
public class Flower {
    @Id
    private String name;
    private Integer count;
    private BigDecimal price;

    public String getName() {
        return name;
    }

    public Integer getCount() {
        return count;
    }

    public BigDecimal getPrice() {
        return price;
    }

    //remove after done debugging
    public void setName(String name) {
        this.name = name;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    //end remove

    @Override
    public String toString() {
        return String.format("name = %s, count = %d, price = %s",
                name, count, price);
    }
}

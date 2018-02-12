package com.accenture.flowershop.be.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;

@Entity
@Table(name = "Orders")
@NamedQueries
({
    @NamedQuery(name = "getOrdersByLogin", query = "Select o from Order o where custlogin = :custlogin")
})
public class Order {
    @Id
    @GeneratedValue
    private Integer orderid = 0; // default
    private String custlogin;
    private BigDecimal total;
    private Date createdate = new Date(System.currentTimeMillis()); // default
    private Date closedate = null; // default
    private Boolean status = false; // default

    public Order(){}
    public Order(String custlogin, BigDecimal total) {
        this.custlogin = custlogin;
        this.total = total;
    }

    public Integer getOrderid() {
        return orderid;
    }

    public void setOrderid(Integer orderid) {
        this.orderid = orderid;
    }

    public String getCustlogin() {
        return custlogin;
    }

    public void setCustlogin(String custlogin) {
        this.custlogin = custlogin;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public Date getClosedate() {
        return closedate;
    }

    public void setClosedate(Date closedate) {
        this.closedate = closedate;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}

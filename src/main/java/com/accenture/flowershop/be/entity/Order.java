package com.accenture.flowershop.be.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;

@Entity
@Table(name = "Orders")
@NamedQueries
({
    @NamedQuery(name = "getOrdersByLogin", query = "Select o from Order o where custlogin = :custlogin"),
    @NamedQuery(name = "getOrderById", query = "Select o from Order o where orderid = :orderid"),
    @NamedQuery(name = "getAllOrdersSortedByDateAndStatus", query = "Select o from Order o order by createdate, status")
})
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer orderid; // default
    private String custlogin;
    private BigDecimal total;
    private Date createdate = new Date(System.currentTimeMillis()); // default
    private Date closedate = null; // default
    private String status = "created"; // default

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return String.format("orderid = %d, custlogin = %s, total = %s, createdate = %s, closedate = %s, status = %s",
                getOrderid(), getCustlogin(), getTotal(), getCreatedate(), getClosedate(), getStatus());
    }
}

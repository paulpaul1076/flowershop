package com.accenture.flowershop.fe.dto;

import java.math.BigDecimal;

public class CartFlower {
    private String name;
    private int howmany;
    private BigDecimal total;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHowmany() {
        return howmany;
    }

    public void setHowmany(int howmany) {
        this.howmany = howmany;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    /*@Override
    public int hashCode() {
        return total.hashCode() ^ howmany;
    }*/

    @Override
    public String toString() {
        return String.format("CartFlower = {name = %s, howmany = %d, total = %s}", name, howmany, total);
    }

    @Override
    public boolean equals(Object o) {
        if(o == null || !(o instanceof CartFlower)) {
            return false;
        }
        CartFlower that = (CartFlower)o;
        return this.howmany == that.howmany
                && this.total.equals(that.total)
                && this.name.equals(that.name);
    }
}

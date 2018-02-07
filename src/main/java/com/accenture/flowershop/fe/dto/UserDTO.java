package com.accenture.flowershop.fe.dto;

import java.math.BigDecimal;

// only what's supposed to be displayed is here
public class UserDTO {
    private String name;
    private int discount;
    private BigDecimal balance;
    public String getName(){
        return name;
    }

    public int getDiscount() {
        return discount;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public UserDTO(String name, int discount, BigDecimal balance) {
        this.name = name;
        this.discount = discount;
        this.balance = balance;
    }
}

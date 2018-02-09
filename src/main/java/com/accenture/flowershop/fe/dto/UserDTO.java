package com.accenture.flowershop.fe.dto;

import com.accenture.flowershop.be.entity.User;

import java.math.BigDecimal;

public class UserDTO {
    private String name;
    private int discount;
    private BigDecimal balance;
    private String address;
    private String phone;

    public UserDTO(User user) {
        this.name = user.getName();
        this.discount = user.getDiscount();
        this.balance = user.getBalance();
        this.address = user.getAddress();
        this.phone = user.getPhone();
    }
    public String getName(){
        return name;
    }


    public int getDiscount() {
        return discount;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }
}

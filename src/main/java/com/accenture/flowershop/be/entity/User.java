package com.accenture.flowershop.be.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "Users")
@NamedQueries
({
    @NamedQuery(name = "getUser", query = "Select u from User u where u.login = :login and u.password = :password"),
    @NamedQuery(name = "doesLoginExist", query = "Select u from User u where u.login = :login")
})
public class User {

    private String address;
    private String phone;

    private Boolean isAdmin = false; // default value

    @Id
    private String login;

    private String password;

    private BigDecimal balance = new BigDecimal("2000"); // default value

    private Integer discount = 3; // default value

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDiscount() {
        return discount;
    }


    public boolean isAdmin() {
        return isAdmin;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public BigDecimal getBalance() {
        return balance;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return String.format("address = %s, phone = %s, isAdmin = %b, login = %s, password = %s, balance = %s, discount = %d, name = %s",
                address, phone, isAdmin, login, password, balance, discount, name);
    }
}

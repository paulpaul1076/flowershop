package com.accenture.flowershop.be.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "Users")
@NamedQueries
({
    @NamedQuery(name = "getUserFromDB", query = "Select u from User u where u.login = :login and u.password = :password"),
    @NamedQuery(name = "isLoginInDB", query = "Select u from User u where u.login = :login")
})
public class User {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;

    private Boolean isAdmin = false; // default value

    @Id
    private String login;

    private String password;

    private BigDecimal balance = new BigDecimal("2000"); // default value

    private int discount = 3; // default value

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

//    public void setDiscount(int discount) {
//        this.discount = discount;
//    }


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

//    public void setBalance(BigDecimal balance) {
//        this.balance = balance;
//    }
}
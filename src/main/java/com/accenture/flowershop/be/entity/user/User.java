package com.accenture.flowershop.be.entity.user;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@NamedQueries({
@NamedQuery(name="loginUser", query = "select id," +
        " isAdmin," +
        " login," +
        " password," +
        " balance," +
        " distcount," +
        " name from Users where login = :login and password=:password"),
@NamedQuery(name="registerUser",
        query = "insert into Users(login, password, discount) " +
                "values(:login, :password, :discount)")})
public class User {

    @Id
    @Column(name = "id")
    private long id;

    @Column(name = "isAdmin")
    private boolean isAdmin;

    @Id
    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "balance")
    private BigDecimal balance;

    @Column(name = "discount")
    private int discount;

    @Column(name = "name")
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

    public void setDiscount(int discount) {
        this.discount = discount;
    }


    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
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

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }


}

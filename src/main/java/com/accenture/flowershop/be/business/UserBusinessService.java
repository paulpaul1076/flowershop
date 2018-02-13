package com.accenture.flowershop.be.business;

import com.accenture.flowershop.be.entity.Flower;
import com.accenture.flowershop.be.entity.User;

import java.math.BigDecimal;
import java.util.List;

public interface UserBusinessService {
    User login(String user, String password);
    boolean register(User user);
    void updateUser(User user);
    User getUserByLogin(String login);
}

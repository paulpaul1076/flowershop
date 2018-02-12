package com.accenture.flowershop.be.access;

import com.accenture.flowershop.be.entity.Flower;
import com.accenture.flowershop.be.entity.User;

import java.math.BigDecimal;
import java.util.List;

public interface UserAccessService {
    boolean putUser(User user);
    User getUser(String login, String password);
    boolean doesLoginExist(String login);
}

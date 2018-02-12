package com.accenture.flowershop.be.business;

import com.accenture.flowershop.be.access.UserAccessService;
import com.accenture.flowershop.be.access.UserAccessServiceImpl;
import com.accenture.flowershop.be.entity.Flower;
import com.accenture.flowershop.be.entity.User;
import com.accenture.flowershop.fe.dto.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.List;

public class UserBusinessServiceImpl implements UserBusinessService {

    private UserAccessService dao;

    private static final Logger LOG = LoggerFactory.getLogger(UserBusinessServiceImpl.class);

    public UserBusinessServiceImpl(UserAccessService dao) {
        this.dao = dao;
    }

    @Override
    public User login(String login, String password) {
        User user = dao.getUser(login, password);
        if(user != null) {
            return user;
        }
        return null;
    }

    @Override
    public boolean register(User user) {
        return dao.putUser(user);
    }

}

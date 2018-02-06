package com.accenture.flowershop.be.business.user;

import com.accenture.flowershop.be.access.UserDAO;
import com.accenture.flowershop.be.business.user.UserBusinessService;
import com.accenture.flowershop.be.entity.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.PrintStream;

public class UserBusinessServiceImpl implements UserBusinessService {

    private UserDAO dao;

    private static final Logger LOG = LoggerFactory.getLogger(UserBusinessServiceImpl.class);

    public UserBusinessServiceImpl(UserDAO dao) {
        LOG.debug("UserBusinessServiceImpl bean created");
        this.dao = dao;
    }

    @Override
    public String login(String user, String password) {
        return null;
    }

    @Override
    public User register(String user, String password, String address) {
        return null;
    }
}

package com.accenture.flowershop.be.business.user;

import com.accenture.flowershop.be.access.UserAccessService;
import com.accenture.flowershop.be.access.UserDAO;
import com.accenture.flowershop.be.business.user.UserBusinessService;
import com.accenture.flowershop.be.entity.user.User;
import com.accenture.flowershop.fe.dto.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.PrintStream;

public class UserBusinessServiceImpl implements UserBusinessService {

    private UserAccessService dao;

    private static final Logger LOG = LoggerFactory.getLogger(UserBusinessServiceImpl.class);

    public UserBusinessServiceImpl(UserAccessService dao) {
        LOG.debug("UserBusinessServiceImpl bean created");
        this.dao = dao;
    }

    @Override
    public UserDTO login(String login, String password) {
        User user = dao.getUserFromDB(login, password);
        if(user != null) {
            return new UserDTO(user.getName(), user.getDiscount(), user.getBalance());
        }
        return null;
    }

    @Override
    public UserDTO register(String login, String password, String name) {
        User user = dao.putUserIntoDB(login, password, name);
        if(user != null) {
            return new UserDTO(user.getName(), user.getDiscount(), user.getBalance());
        }
        return null;
    }
}

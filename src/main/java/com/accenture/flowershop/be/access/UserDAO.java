package com.accenture.flowershop.be.access;

import com.accenture.flowershop.be.entity.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.PrintStream;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Deprecated
public class UserDAO {
    private static final Logger LOG = LoggerFactory.getLogger(UserDAO.class);
    public UserDAO() {
        LOG.debug("UserDAO bean created");
        fakeDB = new HashMap<>();
        populateUsers();
    }
    public boolean doesThisUserExist(User user) {
        return false;
    }

    private void populateUsers(){
        User admin = new User();
        admin.setAdmin(true);
        admin.setLogin("admin");
        admin.setPassword("password");
        admin.setBalance(new BigDecimal("2000"));
        fakeDB.put(admin.getLogin() + " " + admin.getPassword(), admin);

        User pavel = new User();
        admin.setAdmin(false);
        admin.setLogin("paulpaul1076");
        admin.setPassword("123");
        admin.setBalance(new BigDecimal("2000000"));
        fakeDB.put(pavel.getLogin() + " " + pavel.getPassword(), pavel);
    }

    private Map<String, User> fakeDB;
}

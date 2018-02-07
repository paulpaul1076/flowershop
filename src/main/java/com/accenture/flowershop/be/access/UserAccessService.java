package com.accenture.flowershop.be.access;

import com.accenture.flowershop.be.entity.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class UserAccessService {
    private static final Logger LOG = LoggerFactory.getLogger(UserAccessService.class);
    public UserAccessService() {
        LOG.debug("UserAccessService bean created");
        fakeDB = new HashMap<>();
        populateUsers();
    }
//    public boolean doesThisUserExist(User user) {
//        return false;
//    }

    public User getUserFromDB(String name, String password) {
        return fakeDB.get(name.trim() + " " + password.trim());
    }

    public User putUserIntoDB(String login, String password, String name) {
        if(fakeDB.containsKey(login)) return null;
        User user = new User();
        user.setName(name);
        user.setAdmin(false);
        user.setBalance(new BigDecimal("2000"));
        user.setDiscount(3);
        user.setLogin(login);
        user.setPassword(password);
        fakeDB.put(login.trim() + " " + password.trim(), user);
        return user;
    }

    private void populateUsers(){
        User admin = new User();
        admin.setAdmin(true);
        admin.setLogin("admin");
        admin.setName("John");
        admin.setPassword("admin123");
        admin.setBalance(new BigDecimal("2000"));
        fakeDB.put(admin.getLogin() + " " + admin.getPassword(), admin);

        User pavel = new User();
        pavel.setAdmin(false);
        pavel.setLogin("paulpaul1076");
        pavel.setName("Pavel");
        pavel.setPassword("123");
        pavel.setBalance(new BigDecimal("2000000"));
        fakeDB.put(pavel.getLogin() + " " + pavel.getPassword(), pavel);
    }

    private Map<String, User> fakeDB;
}

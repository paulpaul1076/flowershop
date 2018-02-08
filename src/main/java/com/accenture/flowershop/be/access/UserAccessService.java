package com.accenture.flowershop.be.access;

import com.accenture.flowershop.be.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserAccessService {
    @PersistenceContext
    private EntityManager entityManager;

    //private EntityManagerFactory factory;

    private static final Logger LOG = LoggerFactory.getLogger(UserAccessService.class);

    public UserAccessService() {
        LOG.debug("Entity manager = {}", entityManager);
        LOG.debug("UserAccessService bean created");
    }

    public User getUserFromDB(String login, String password) {
        TypedQuery<User> query = entityManager.createNamedQuery("loginUser", User.class);
        query.setParameter("login", login);
        query.setParameter("password", password);
        List<User> list = query.getResultList();
        if(list.size() != 0) return list.get(0);
        return null;
    }

    // FIXME: 07.02.2018 add logic to retrieve users from a database
    public User putUserIntoDB(String login, String password, String name) {
        //old begin
        /*if (fakeDB.containsKey(login)) return null;
        User user = new User();
        user.setName(name);
        user.setAdmin(false);
        user.setBalance(new BigDecimal("2000"));
        user.setDiscount(3);
        user.setLogin(login);
        user.setPassword(password);
        fakeDB.put(login.trim(), user);
        return user;*/
        //old end
        //if(getUserFromDB(login))
        //old end
        User user = new User();
        user.setName(name);
        user.setAdmin(false);
        user.setBalance(new BigDecimal("2000"));
        user.setDiscount(3);
        user.setLogin(login);
        user.setPassword(password);
        entityManager.persist(user);
        entityManager.flush();
        return null; // broken
    }

    private void populateUsers() {
        User admin = new User();
        admin.setAdmin(true);
        admin.setLogin("admin");
        admin.setName("Admin");
        admin.setPassword("admin123");
        admin.setBalance(new BigDecimal("2000"));
        fakeDB.put(admin.getLogin(), admin);

        User pavel = new User();
        pavel.setAdmin(false);
        pavel.setLogin("paulpaul1076");
        pavel.setName("Pavel");
        pavel.setPassword("123");
        pavel.setBalance(new BigDecimal("2000000"));
        fakeDB.put(pavel.getLogin(), pavel);
    }

    private Map<String, User> fakeDB;
}

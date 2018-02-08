package com.accenture.flowershop.be.access;

import com.accenture.flowershop.be.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

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
        TypedQuery<User> query = entityManager.createNamedQuery("getUserFromDB", User.class);
        query.setParameter("login", login);
        query.setParameter("password", password);
        List<User> list = query.getResultList();
        if(list.size() != 0) return list.get(0);
        return null;
    }

    public boolean isLoginInDB(String login) {
        TypedQuery<User> query = entityManager.createNamedQuery("isLoginInDB", User.class);
        query.setParameter("login", login);
        List<User> list = query.getResultList();
        return list.size() != 0;
    }

    // return null if such login is already registered, otherwise, newly created user
    @Transactional
    public User putUserIntoDB(String login, String password, String name) {
        if(isLoginInDB(login)) return null;
        User user = new User();
        user.setName(name);
        //user.setAdmin(false);
        //user.setBalance(new BigDecimal("2000"));
        //user.setDiscount(3);
        user.setLogin(login);
        user.setPassword(password);
        entityManager.persist(user);
        return user;
    }
}

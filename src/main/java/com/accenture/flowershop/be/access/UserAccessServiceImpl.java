package com.accenture.flowershop.be.access;

import com.accenture.flowershop.be.entity.Flower;
import com.accenture.flowershop.be.entity.User;
import com.accenture.flowershop.be.xml.UserMarshallingServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

public class UserAccessServiceImpl implements UserAccessService{
    @PersistenceContext
    private EntityManager entityManager;
    //private UserMarshallingServiceImpl marshallingService = new UserMarshallingServiceImpl();

    private static final Logger LOG = LoggerFactory.getLogger(UserAccessServiceImpl.class);

    public UserAccessServiceImpl() {
    }

    public User getUser(String login, String password) {
        TypedQuery<User> query = entityManager.createNamedQuery("getUser", User.class);
        query.setParameter("login", login);
        query.setParameter("password", password);
        List<User> list = query.getResultList();
        if(!list.isEmpty()) return list.get(0);
        return null;
    }

    public boolean doesLoginExist(String login) {
        TypedQuery<User> query = entityManager.createNamedQuery("getUserByLogin", User.class);
        query.setParameter("login", login);
        List<User> list = query.getResultList();
        return !list.isEmpty();
    }

    // return false if such login is already registered, otherwise, newly created user
    @Transactional
    public boolean putUser(User user) {
        if(doesLoginExist(user.getLogin())) {
            return false;
        }
        entityManager.persist(user); // sure that it's merge?
        //marshallingService.marshall(null,null);
        return true;
    }

    @Transactional
    @Override
    public void updateUser(User user) {
        entityManager.merge(user);
    }

    @Override
    public User getUserByLogin(String login) {
        TypedQuery<User> query = entityManager.createNamedQuery("getUserByLogin", User.class);
        query.setParameter("login", login);
        List<User> list = query.getResultList();
        if(list.isEmpty()) return null;
        return list.get(0);
    }


}

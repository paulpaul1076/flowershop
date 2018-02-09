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
        if(list.size() != 0) return list.get(0);
        return null;
    }

    public boolean doesLoginExist(String login) {
        TypedQuery<User> query = entityManager.createNamedQuery("doesLoginExist", User.class);
        query.setParameter("login", login);
        List<User> list = query.getResultList();
        return !list.isEmpty();
    }

    @Override
    public List<Flower> getAllFlowers() {
        TypedQuery<Flower> query = entityManager.createNamedQuery("getAllFlowers", Flower.class);
        return query.getResultList();
    }

    @Override
    public List<Flower> getFlowersWithPriceBounds(BigDecimal from, BigDecimal to) {
        if(from.compareTo(to) > 0) {
            return null;
        }
        TypedQuery<Flower> query = entityManager.createNamedQuery("getFlowersWithPriceBounds", Flower.class);
        query.setParameter("from", from);
        query.setParameter("to", to);
        return query.getResultList();
    }

    @Override
    public List<Flower> getFlowersByName(String substring) {
        TypedQuery<Flower> query = entityManager.createNamedQuery("getFlowersByName", Flower.class);
        query.setParameter("substring", substring);
        return query.getResultList();
    }

    // return false if such login is already registered, otherwise, newly created user
    @Transactional
    public boolean putUser(User user) {
        if(doesLoginExist(user.getLogin())) {
            return false;
        }
        entityManager.persist(user);
        //marshallingService.marshall(null,null);
        return true;
    }
}

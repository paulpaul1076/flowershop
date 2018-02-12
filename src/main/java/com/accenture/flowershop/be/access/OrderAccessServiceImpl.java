package com.accenture.flowershop.be.access;

import com.accenture.flowershop.be.entity.Order;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

public class OrderAccessServiceImpl implements OrderAccessService {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Order> getAllCustomersOrders(String custlogin) {
        TypedQuery<Order> query = entityManager.createNamedQuery("getOrdersByLogin", Order.class);
        query.setParameter("custlogin", custlogin);
        List<Order> list = query.getResultList();
        return list;
    }

    @Transactional
    @Override
    public void putOrder(Order order) {
        // would you ever need to check if such order exists?
        // for now: no, and the return type is void
        entityManager.persist(order);
    }

}

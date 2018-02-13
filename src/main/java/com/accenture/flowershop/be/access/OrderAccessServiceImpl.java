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
    public Order putOrder(Order order) {
        return entityManager.merge(order);
    }

    public Order getOrderById(Integer orderid) {
        TypedQuery<Order> query = entityManager.createNamedQuery("getOrderById", Order.class);
        query.setParameter("orderid", orderid);
        List<Order> list = query.getResultList();
        if(list.isEmpty()) return null;
        return list.get(0);
    }

    @Override
    public List<Order> getAllOrdersSortedByDateAndStatus() {
        TypedQuery<Order> query = entityManager.createNamedQuery("getAllOrdersSortedByDateAndStatus", Order.class);
        List<Order> list = query.getResultList();
        return list;
    }



}

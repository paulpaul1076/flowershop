package com.accenture.flowershop.be.business;

import com.accenture.flowershop.be.access.OrderAccessService;
import com.accenture.flowershop.be.entity.Order;
import com.accenture.flowershop.fe.dto.UserDTO;

import java.math.BigDecimal;
import java.util.List;

public class OrderBusinessServiceImpl implements OrderBusinessService {
    private OrderAccessService dao;

    public OrderBusinessServiceImpl(OrderAccessService dao) {
        this.dao = dao;
    }
    @Override
    public List<Order> getAllCustomersOrders(String custlogin) {
        return dao.getAllCustomersOrders(custlogin);
    }

    @Override
    public void createOrder(Order order) {
        //write logic to handle a situation where user doesn't have enough money no
        dao.putOrder(order);
    }
}

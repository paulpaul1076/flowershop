package com.accenture.flowershop.be.access;

import com.accenture.flowershop.be.entity.Order;

import java.util.List;

public interface OrderAccessService {
    List<Order> getAllCustomersOrders(String custlogin);
    Order putOrder(Order order);
    Order getOrderById(Integer orderid);
    List<Order> getAllOrdersSortedByDateAndStatus();

}

package com.accenture.flowershop.be.business;

import com.accenture.flowershop.be.entity.Order;
import com.accenture.flowershop.fe.dto.UserDTO;

import java.util.List;

public interface OrderBusinessService {
    List<Order> getAllCustomersOrders(String custlogin);
    void createOrder(Order order);
}

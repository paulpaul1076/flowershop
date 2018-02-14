package com.accenture.flowershop.be.business;

import com.accenture.flowershop.be.entity.Order;
import com.accenture.flowershop.fe.dto.CartFlower;
import com.accenture.flowershop.fe.dto.UserDTO;

import java.math.BigDecimal;
import java.util.List;

public interface OrderBusinessService {
    List<Order> getAllCustomersOrders(String custlogin);
    List<Order> getAllOrdersSortedByDateAndStatus();
    void createOrUpdateOrder(Order order);
    void payOrder(String login, Integer orderid) throws BusinessLogicException;
    void placeOrder(String login, BigDecimal total, List<CartFlower> cartlist);
    void closeOrder(Integer orderid);
    CartFlower addToCart(String login,
                         List<CartFlower> cartlist,
                         String flowerName,
                         String numberOfFlowers)throws BusinessLogicException;
}

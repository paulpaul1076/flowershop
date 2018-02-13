package com.accenture.flowershop.be.business;

import com.accenture.flowershop.be.access.OrderAccessService;
import com.accenture.flowershop.be.entity.Flower;
import com.accenture.flowershop.be.entity.Order;
import com.accenture.flowershop.be.entity.User;
import com.accenture.flowershop.fe.dto.CartFlower;
import com.accenture.flowershop.fe.dto.UserDTO;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

public class OrderBusinessServiceImpl implements OrderBusinessService {
    private OrderAccessService dao;
    private UserBusinessService userBusinessService;
    private FlowerBusinessService flowerBusinessService;

    public OrderBusinessServiceImpl(OrderAccessService dao,
                                    UserBusinessService userBusinessService,
                                    FlowerBusinessService flowerBusinessService) {
        this.dao = dao;
        this.userBusinessService = userBusinessService;
        this.flowerBusinessService = flowerBusinessService;
    }
    @Transactional
    @Override
    public List<Order> getAllCustomersOrders(String custlogin) {
        return dao.getAllCustomersOrders(custlogin);
    }

    @Transactional
    @Override
    public void createOrUpdateOrder(Order order) {
        dao.putOrder(order);
    }

    @Transactional
    public void payOrder(String login, Integer orderid)throws Exception {

        Order order = dao.getOrderById(orderid);

        BigDecimal ordercost = order.getTotal();

        User user = userBusinessService.getUserByLogin(login); // may be null but not in this context

        BigDecimal usersMoney = user.getBalance();
        if(usersMoney.compareTo(ordercost) < 0) { // if not enough money then display error message and leave this method
            throw new Exception("You don't have enough money to pay for this order!");
        }

        //else
        //update order everywhere

        order.setStatus("paid");
        dao.putOrder(order);

        //update user everywhere
        BigDecimal moneyLeft = usersMoney.subtract(ordercost);
        user.setBalance(moneyLeft);
        userBusinessService.updateUser(user);
    }

    @Transactional
    public void placeOrder(String login, BigDecimal total, List<CartFlower> cartlist) {
        Order order = new Order(login, total);
        // do i need the line below?
        this.createOrUpdateOrder(order); // have to assign to get a new id back
        List<Flower> flowerlist = flowerBusinessService.getAllFlowers();
        for(CartFlower cartFlower : cartlist) {
            for(Flower flower : flowerlist) {
                if(cartFlower.getName().equals(flower.getName())) {
                    flower.setCount(flower.getCount() - cartFlower.getHowmany());
                    flowerBusinessService.updateFlower(flower);
                }
            }
        }
    }

    @Transactional
    @Override
    public List<Order> getAllOrdersSortedByDateAndStatus(){
        return dao.getAllOrdersSortedByDateAndStatus();
    }

    @Transactional
    @Override
    public void closeOrder(Integer orderid) {
        Order order = dao.getOrderById(orderid);
        order.setClosedate(new Date(System.currentTimeMillis()));
        order.setStatus("closed");
    }

    @Transactional
    public void addToCart(){

    }
}

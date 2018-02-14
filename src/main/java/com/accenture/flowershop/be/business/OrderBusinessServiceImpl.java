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
    public void payOrder(String login, Integer orderid)throws BusinessLogicException {

        Order order = dao.getOrderById(orderid);

        BigDecimal ordercost = order.getTotal();

        User user = userBusinessService.getUserByLogin(login); // may be null but not in this context

        BigDecimal usersMoney = user.getBalance();
        if(usersMoney.compareTo(ordercost) < 0) { // if not enough money then display error message and leave this method
            throw new BusinessLogicException("You don't have enough money to pay for this order!");
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
                    //flower.setCount(flower.getCount() - cartFlower.getHowmany());
                    //flowerBusinessService.updateFlower(flower);
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
    public CartFlower addToCart(String login,
                          List<CartFlower> cartlist,
                          String flowerName,
                          String howManyToAddStr)
            throws BusinessLogicException {

        List<Flower> flowerlist =  flowerBusinessService.getAllFlowers();
        User user = userBusinessService.getUserByLogin(login);
        int discount = user.getDiscount();
        CartFlower newFlower = new CartFlower();
        BigDecimal totalPriceForFlower = null;
        int howManyLeftInStock = 0;

        // page stuff
        // checks if the field with count of flowers is not empty or equal to zero
        if(howManyToAddStr.isEmpty()) {
            throw new BusinessLogicException("The \"How many?\" field cannot be empty!");
        }

        int howManyToAdd = Integer.parseInt(howManyToAddStr);

        if(howManyToAdd <= 0) {
            throw new BusinessLogicException("You can't order 0 or a negative number of something!");
        }

        for(Flower f : flowerlist) {
            if(f.getName().equals(flowerName)) {
                howManyLeftInStock = f.getCount(); // found flower
                if(howManyLeftInStock - howManyToAdd < 0) {
                    throw new BusinessLogicException("You cannot order more than what's left in stock!");
                }
                // the flowers are added back to database from cart if the order weren't made
                f.setCount(howManyLeftInStock - howManyToAdd);

                totalPriceForFlower = f.getPrice()
                        .multiply(BigDecimal.valueOf(howManyToAdd))
                        .multiply(BigDecimal.valueOf((100.0 - discount)/100.0)).setScale(2);

                System.out.println("Howmanytoadd = " + howManyToAdd);
                System.out.println("name = " + f.getName());
                System.out.println("total = " + totalPriceForFlower);

                newFlower.setHowmany(howManyToAdd);
                newFlower.setName(f.getName());
                newFlower.setTotal(totalPriceForFlower);

                break;
            }
        }
        // at this point we have created our new cartflower and set its attributes
        return newFlower;
    }
}

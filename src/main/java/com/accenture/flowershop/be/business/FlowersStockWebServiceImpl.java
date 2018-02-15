package com.accenture.flowershop.be.business;

import com.accenture.flowershop.be.access.FlowerAccessService;
import com.accenture.flowershop.be.entity.Flower;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.annotation.PostConstruct;
import javax.jws.WebService;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import java.util.List;

@WebService
(
    endpointInterface = "com.accenture.flowershop.be.business.FlowersStockWebService",
    serviceName = "flowersStockWebService"
)
public class FlowersStockWebServiceImpl implements FlowersStockWebService {
    @Autowired
    public FlowerAccessService flowerAccessService;

    @PostConstruct
    public void init(){
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
        System.out.println("Post construct worked!");
        System.out.println("flowreAccessService = " + flowerAccessService);
    }

    @Transactional
    @Override
    public void increaseFlowersStockSize(int count) {
        List<Flower> flowerList = flowerAccessService.getAllFlowers();
        for(Flower flower : flowerList) {
            flower.setCount(flower.getCount() + count);
        }
    }
}

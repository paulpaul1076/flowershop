package com.accenture.flowershop.be.business;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface FlowersStockWebService {
    @WebMethod
    void increaseFlowersStockSize(int count);
}

package com.accenture.flowershop.be.business;

import com.accenture.flowershop.be.access.FlowerAccessService;
import com.accenture.flowershop.be.entity.Flower;
import com.accenture.flowershop.be.entity.User;

import java.math.BigDecimal;
import java.util.List;

public class FlowerBusinessServiceImpl implements FlowerBusinessService {
    private FlowerAccessService dao;

    public FlowerBusinessServiceImpl(FlowerAccessService dao){
        this.dao = dao;
    }


    @Override
    public List<Flower> getAllFlowers() {
        return dao.getAllFlowers();
    }

    @Override
    public List<Flower> getFlowersWithPriceBounds(BigDecimal from, BigDecimal to) {
        return dao.getFlowersWithPriceBounds(from, to);
    }

    @Override
    public List<Flower> getFlowersByName(String substring) {
        return dao.getFlowersByName(substring);
    }

    public void updateFlower(Flower flower) {
        dao.updateFlower(flower);
    }

}

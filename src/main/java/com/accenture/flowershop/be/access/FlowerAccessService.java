package com.accenture.flowershop.be.access;

import com.accenture.flowershop.be.entity.Flower;

import java.math.BigDecimal;
import java.util.List;

public interface FlowerAccessService {
    List<Flower> getAllFlowers();
    List<Flower> getFlowersWithPriceBounds(BigDecimal from, BigDecimal to);
    List<Flower> getFlowersByName(String substring);
    void updateFlower(Flower flower);
}

package com.accenture.flowershop.be.access;

import com.accenture.flowershop.be.entity.QFlower;
import com.querydsl.core.types.Predicate;

import java.math.BigDecimal;

public class FlowerPredicates {

    private FlowerPredicates() {}

    public static Predicate hasNameIgnoreCase(String searchTerm) {
        if(searchTerm == null || searchTerm.isEmpty()) {
            return QFlower.flower.isNotNull();
        } else {
            return QFlower.flower.name.containsIgnoreCase(searchTerm);
        }
    }

    public static Predicate isInBounds(BigDecimal from, BigDecimal to) {
        return QFlower.flower.price.between(from, to);
    }
}

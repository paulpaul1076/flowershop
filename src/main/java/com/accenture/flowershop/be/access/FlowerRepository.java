package com.accenture.flowershop.be.access;

import com.accenture.flowershop.be.entity.Flower;
import com.querydsl.core.types.Predicate;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.Repository;


public interface FlowerRepository extends Repository<Flower, String>, QuerydslPredicateExecutor<Flower> {
    // can be a predicate
    // 1) with specified bounds
    // 2) with a name

    Iterable<Flower> findAll(Predicate predicate);
}

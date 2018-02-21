package com.accenture.flowershop.be.access;

import com.accenture.flowershop.be.entity.Flower;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class FlowerAccessServiceImpl implements FlowerAccessService{
    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public List<Flower> getAllFlowers() {
        TypedQuery<Flower> query = entityManager.createNamedQuery("getAllFlowers", Flower.class);
        return query.getResultList();
    }

    @Override
    public List<Flower> getFlowersWithPriceBounds(BigDecimal from, BigDecimal to) {
        if(from.compareTo(to) > 0) {
            return null;
        }
        TypedQuery<Flower> query = entityManager.createNamedQuery("getFlowersWithPriceBounds", Flower.class);
        query.setParameter("from", from);
        query.setParameter("to", to);
        return query.getResultList();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Flower> getFlowersByName(String substring) {
        TypedQuery<Flower> query = entityManager.createNamedQuery("getFlowersByName", Flower.class);
        substring = "%" + substring.toLowerCase() + "%";
        query.setParameter("substring", substring);
        return query.getResultList();
    }

    @Transactional
    @Override
    public void updateFlower(Flower flower) {
        entityManager.merge(flower);
    }
}

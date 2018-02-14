package com.accenture.flowershop.be.business;

import com.accenture.flowershop.be.access.FlowerAccessService;
import com.accenture.flowershop.be.entity.Flower;
import com.accenture.flowershop.be.entity.User;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    public List<BigDecimal> validateBoundInput(String rangeInput) throws BusinessLogicException {
        Pattern RANGE_INPUT_PAT = Pattern.compile("from [1-9]{1}[0-9]*(.[0-9]{1,}){0,1} to [1-9]{1}[0-9]*(.[0-9]{1,}){0,1}");
        Matcher m = RANGE_INPUT_PAT.matcher(rangeInput);
        if(!m.matches()) { // handle this error
            throw new BusinessLogicException("You have entered a search query in a bad pattern, should be: \"From a to b\" where a and b are numbers");
        }

        Pattern numberPattern = Pattern.compile("[1-9]{1}[0-9]*(.[0-9]{1,}){0,1}");
        Matcher numberMatcher = numberPattern.matcher(rangeInput);
        numberMatcher.find();
        String aStr = numberMatcher.group();
        numberMatcher.find();
        String bStr = numberMatcher.group();
        BigDecimal a = new BigDecimal(aStr).setScale(2);
        BigDecimal b = new BigDecimal(bStr).setScale(2);
        if(a.compareTo(b) > 0) { // handle this error
            throw new BusinessLogicException("You have entered a search query in which a > b, a must be <= b");
        }
        ArrayList<BigDecimal> result = new ArrayList<>();
        result.add(a); result.add(b);
        return result;
    }

}

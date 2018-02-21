package com.accenture.flowershop.be.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QOrder is a Querydsl query type for Order
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QOrder extends EntityPathBase<Order> {

    private static final long serialVersionUID = -802570002L;

    public static final QOrder order = new QOrder("order1");

    public final DatePath<java.sql.Date> closedate = createDate("closedate", java.sql.Date.class);

    public final DatePath<java.sql.Date> createdate = createDate("createdate", java.sql.Date.class);

    public final StringPath custlogin = createString("custlogin");

    public final NumberPath<Integer> orderid = createNumber("orderid", Integer.class);

    public final StringPath status = createString("status");

    public final NumberPath<java.math.BigDecimal> total = createNumber("total", java.math.BigDecimal.class);

    public QOrder(String variable) {
        super(Order.class, forVariable(variable));
    }

    public QOrder(Path<? extends Order> path) {
        super(path.getType(), path.getMetadata());
    }

    public QOrder(PathMetadata metadata) {
        super(Order.class, metadata);
    }

}


package com.accenture.flowershop.be.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QFlower is a Querydsl query type for Flower
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QFlower extends EntityPathBase<Flower> {

    private static final long serialVersionUID = 627274939L;

    public static final QFlower flower = new QFlower("flower");

    public final NumberPath<Integer> count = createNumber("count", Integer.class);

    public final StringPath name = createString("name");

    public final NumberPath<java.math.BigDecimal> price = createNumber("price", java.math.BigDecimal.class);

    public QFlower(String variable) {
        super(Flower.class, forVariable(variable));
    }

    public QFlower(Path<? extends Flower> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFlower(PathMetadata metadata) {
        super(Flower.class, metadata);
    }

}


package com.rent.rentshop.product.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.rent.rentshop.product.domain.Product;

import javax.persistence.EntityManager;
import java.util.List;

import static com.rent.rentshop.member.domain.QUser.user;
import static com.rent.rentshop.product.domain.QProduct.product;

public class ProductRepositoryImpl implements ProductRepositoryCustom {

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    public ProductRepositoryImpl(EntityManager em) {
        this.em = em;
        queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<Product> getMyProducts(Long userId) {

        List<Product> result = queryFactory
                .select(product)
                .from(product)
                .join(product.user, user)
                .where(user.id.eq(userId))
                .fetch();
        return result;

    }

    @Override
    public List<Product> getBest10Products(String city) {

        List<Product> result = queryFactory
                .select(product)
                .from(product)
                .where(product.city.eq(city))
                .orderBy(product.hit.desc())
                .limit(10)
                .fetch();
        return result;

    }

}

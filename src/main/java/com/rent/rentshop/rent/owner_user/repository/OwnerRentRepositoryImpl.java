package com.rent.rentshop.rent.owner_user.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.rent.rentshop.rent.domain.Rent;

import javax.persistence.EntityManager;
import java.util.List;

import static com.rent.rentshop.member.domain.QUser.user;
import static com.rent.rentshop.product.domain.QProduct.product;
import static com.rent.rentshop.rent.domain.QRent.rent;

public class OwnerRentRepositoryImpl implements OwnerRentRepositoryCustom {

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    public OwnerRentRepositoryImpl(EntityManager em) {
        this.em = em;
        queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<Rent> getMyProductRentalUserList(Long userId) {
        // 한방쿼리가 쉽지 않을 것 같다.
        // 일단 상품테이블에서 내 상품아이디를 가져온다.
        List<Long> productIds = queryFactory.select(product.id)
                .from(product)
                .join(product.user, user)
                .where(user.id.eq(userId))
                .fetch();

        // 상품아이디를 통해 IN절로 내 상품에 대여를 신청한 사람들의 목록을 가져온다.
        List<Rent> result = queryFactory.select(rent)
                .from(rent)
                .join(rent.product, product)
                .join(rent.user, user)
                .where(product.id.in(productIds))
                .fetch();

        return result;
    }

}

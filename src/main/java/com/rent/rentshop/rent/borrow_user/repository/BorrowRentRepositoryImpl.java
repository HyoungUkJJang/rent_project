package com.rent.rentshop.rent.borrow_user.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.rent.rentshop.rent.domain.QRent;
import com.rent.rentshop.rent.domain.Rent;

import javax.persistence.EntityManager;
import java.util.List;

import static com.rent.rentshop.member.domain.QUser.user;
import static com.rent.rentshop.product.domain.QProduct.product;
import static com.rent.rentshop.rent.domain.QRent.rent;

public class BorrowRentRepositoryImpl implements BorrowRentRepositoryCustom {

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    public BorrowRentRepositoryImpl(EntityManager em) {
        this.em = em;
        queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<Rent> getMyApplyRentList(Long userId) {
        List<Rent> result = queryFactory
                .select(rent)
                .from(rent)
                .join(rent.user, user)
                .join(rent.product, product)
                .where(user.id.eq(userId))
                .fetch();
        return result;
    }

    @Override
    public Rent returnedMyRental(Long productId, Long userId) {
        Rent result = queryFactory
                .select(QRent.rent)
                .from(QRent.rent)
                .join(QRent.rent.user, user)
                .join(QRent.rent.product, product)
                .where(user.id.eq(userId).and(product.id.eq(productId)))
                .fetchFirst();
        return result;
    }

}

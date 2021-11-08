package com.rent.rentshop.rent.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.rent.rentshop.rent.domain.Rent;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static com.rent.rentshop.member.domain.QUser.user;
import static com.rent.rentshop.product.domain.QProduct.product;
import static com.rent.rentshop.rent.domain.QRent.rent;

public class RentRepositoryImpl implements RentRepositoryCustom {

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    public RentRepositoryImpl(EntityManager em) {
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

}

package com.rent.rentshop.rent.owner_user.repository;

import com.rent.rentshop.rent.domain.Rent;

import java.util.List;

public interface OwnerRentRepositoryCustom {

    /**
     * 사용자가 자신의 상품을 대여신청한 목록을 조회하여 리턴합니다.
     * @param userId 상품의 주인
     * @return 신청이 들어온 상품목록
     */
    List<Rent> getMyProductRentalUserList(Long userId);

}

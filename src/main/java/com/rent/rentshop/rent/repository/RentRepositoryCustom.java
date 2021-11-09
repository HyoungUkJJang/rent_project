package com.rent.rentshop.rent.repository;

import com.rent.rentshop.rent.domain.Rent;

import java.util.List;

/**
 * 상품대여와 관련된 QueryDsl 레포지토리
 */
public interface RentRepositoryCustom {

    /**
     * 사용자가 신청한 대여상품 목록을 조회하여 리턴합니다.
     * @param userId 대여자 아이디
     * @return 대여 신청한 상품목록
     */
    List<Rent> getMyApplyRentList(Long userId);

    /**
     * 사용자가 빌린 상품을 반납신청 합니다.
     * @param productId 빌린 상품의 아이디
     * @param userId 빌린 유저의 아이디
     * @return 빌렸던 상품
     */
    Rent returnedMyRental(Long productId, Long userId);

    /**
     * 사용자가 자신의 상품을 대여신청한 목록을 조회하여 리턴합니다.
     * @param userId 상품의 주인
     * @return 신청이 들어온 상품목록
     */
    List<Rent> getMyProductRentalUserList(Long userId);

}

package com.rent.rentshop.rent.domain;

/**
 * 상품 대여상태
 */
public enum RentStatus {
    WAIT, // 상품대여 대기중
    RENTAL, // 상품 대여중
    RETURN //  상품 반납완료
}

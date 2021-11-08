package com.rent.rentshop.error;

public class RentNotFoundException extends RuntimeException {
    public RentNotFoundException() {
        super("대여 상품을 찾을 수 없습니다.");
    }
}

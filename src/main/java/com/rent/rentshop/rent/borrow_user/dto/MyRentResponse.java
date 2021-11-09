package com.rent.rentshop.rent.borrow_user.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * 내가 빌린 상품의 응답 객체
 */
@Getter
public class MyRentResponse {

    private String productName;
    private int productPrice;
    private int deposit;
    private String ownerUserEmail;
    private LocalDateTime rentalDate;
    private LocalDateTime returnDate;

    MyRentResponse() {}

    @Builder
    public MyRentResponse(String productName, int productPrice, int deposit, String ownerUserEmail, LocalDateTime rentalDate, LocalDateTime returnDate) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.deposit = deposit;
        this.ownerUserEmail = ownerUserEmail;
        this.rentalDate = rentalDate;
        this.returnDate = returnDate;
    }

}

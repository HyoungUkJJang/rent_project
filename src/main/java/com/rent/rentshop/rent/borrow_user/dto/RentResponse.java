package com.rent.rentshop.rent.borrow_user.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * 상품 대여 성공시 응답
 */
@Getter
public class RentResponse {

    private Long id;
    private String productName;
    private LocalDateTime rentalDate;
    private LocalDateTime returnDate;

    @Builder
    public RentResponse(Long id, String productName, LocalDateTime rentalDate, LocalDateTime returnDate) {
        this.id = id;
        this.productName = productName;
        this.rentalDate = rentalDate;
        this.returnDate = returnDate;
    }

    RentResponse() {};

}

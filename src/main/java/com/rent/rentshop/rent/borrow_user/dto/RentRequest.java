package com.rent.rentshop.rent.borrow_user.dto;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

/**
 * 상품대여 신청 폼
 */
@Getter
public class RentRequest {

    private LocalDateTime rentalDate;
    private LocalDateTime returnDate;

    RentRequest() {}

    @Builder
    public RentRequest(LocalDateTime rentalDate, LocalDateTime returnDate) {
        this.rentalDate = rentalDate;
        this.returnDate = returnDate;
    }

}

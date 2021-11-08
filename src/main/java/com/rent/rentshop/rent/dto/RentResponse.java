package com.rent.rentshop.rent.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class RentResponse {

    private Long rentId;
    private String productName;
    private LocalDateTime rentalDate;
    private LocalDateTime returnDate;

    @Builder
    public RentResponse(Long rentId, String productName, LocalDateTime rentalDate, LocalDateTime returnDate) {
        this.rentId = rentId;
        this.productName = productName;
        this.rentalDate = rentalDate;
        this.returnDate = returnDate;
    }

    RentResponse() {};

}

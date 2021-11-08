package com.rent.rentshop.rent.dto;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

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

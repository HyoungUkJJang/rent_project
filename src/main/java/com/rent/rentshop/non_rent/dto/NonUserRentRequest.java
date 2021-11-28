package com.rent.rentshop.non_rent.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class NonUserRentRequest {

    private String name;
    private String phone;
    private LocalDate rentalDate;
    private LocalDate returnDate;

    @Builder
    public NonUserRentRequest(String name, String phone, LocalDate rentalDate, LocalDate returnDate) {
        this.name = name;
        this.phone = phone;
        this.rentalDate = rentalDate;
        this.returnDate = returnDate;
    }

}

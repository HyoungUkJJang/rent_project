package com.rent.rentshop.non_rent.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class NonUserRentResponse {

    private String name;
    private String phone;
    private LocalDate rentalDate;
    private LocalDate returnDate;

    @Builder
    public NonUserRentResponse(String name, String phone, LocalDate rentalDate, LocalDate returnDate) {
        this.name = name;
        this.phone = phone;
        this.rentalDate = rentalDate;
        this.returnDate = returnDate;
    }

}

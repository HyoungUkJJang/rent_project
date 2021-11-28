package com.rent.rentshop.non_rent.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class NonUserRentRequest2 {

    private String name;
    private String phone;
    private String password;
    private LocalDate rentalDate;
    private LocalDate returnDate;

    @Builder
    public NonUserRentRequest2(String name, String phone, String password, LocalDate rentalDate, LocalDate returnDate) {
        this.name = name;
        this.phone = phone;
        this.password = password;
        this.rentalDate = rentalDate;
        this.returnDate = returnDate;
    }

}

package com.rent.rentshop.rent.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class MyRentResponse {

    private String productName;
    private int productPrice;
    private int deposit;
    private String ownerUserMail;
    private LocalDateTime rentalDate;
    private LocalDateTime returnDate;

    MyRentResponse() {}

    @Builder
    public MyRentResponse(String productName, int productPrice, int deposit, String ownerUserMail, LocalDateTime rentalDate, LocalDateTime returnDate) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.deposit = deposit;
        this.ownerUserMail = ownerUserMail;
        this.rentalDate = rentalDate;
        this.returnDate = returnDate;
    }

}

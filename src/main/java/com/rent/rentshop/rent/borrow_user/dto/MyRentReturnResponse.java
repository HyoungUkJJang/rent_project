package com.rent.rentshop.rent.borrow_user.dto;

import com.rent.rentshop.rent.domain.RentStatus;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MyRentReturnResponse {

    private String productName;
    private String ownerUserEmail;
    private RentStatus rentStatus;

    MyRentReturnResponse() {}

    @Builder
    public MyRentReturnResponse(String productName, String ownerUserEmail, RentStatus rentStatus) {
        this.productName = productName;
        this.ownerUserEmail = ownerUserEmail;
        this.rentStatus = rentStatus;
    }

}

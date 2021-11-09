package com.rent.rentshop.rent.owner_user.dto;

import com.rent.rentshop.rent.domain.RentStatus;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ReturnCompleteResponse {

    private String productName;
    private String borrowedUserEmail;
    private RentStatus rentStatus;

    ReturnCompleteResponse() {}

    @Builder
    public ReturnCompleteResponse(String productName, String borrowedUserEmail, RentStatus rentStatus) {
        this.productName = productName;
        this.borrowedUserEmail = borrowedUserEmail;
        this.rentStatus = rentStatus;
    }

}

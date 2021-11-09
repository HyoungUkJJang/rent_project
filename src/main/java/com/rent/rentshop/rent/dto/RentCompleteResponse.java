package com.rent.rentshop.rent.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * 상품대여가 완료되엇을때 내보내는 응답객체
 */
@Getter
public class RentCompleteResponse {

    private Long rentId;
    private String productName;
    private int productPrice;
    private int deposit;
    private String borrowUserEmail;
    private String ownerUserMail;
    private LocalDateTime rentalDate;
    private LocalDateTime returnDate;

    @Builder
    public RentCompleteResponse(Long rentId, String productName, int productPrice, int deposit,
                                String borrowUserEmail, String ownerUserMail, LocalDateTime rentalDate, LocalDateTime returnDate) {
        this.rentId = rentId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.deposit = deposit;
        this.borrowUserEmail = borrowUserEmail;
        this.ownerUserMail = ownerUserMail;
        this.rentalDate = rentalDate;
        this.returnDate = returnDate;
    }

}


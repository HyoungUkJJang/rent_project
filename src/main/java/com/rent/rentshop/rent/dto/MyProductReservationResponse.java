package com.rent.rentshop.rent.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * 내 상품을 빌리려는 정보의 응답객체
 */
@Getter
public class MyProductReservationResponse {

    private Long rentId;
    private String borrowUserEmail;
    private String ownerUserEmail;
    private String productName;
    private LocalDateTime rentalDate;
    private LocalDateTime returnDate;

    @Builder
    public MyProductReservationResponse(Long rentId, String borrowUserEmail, String ownerUserEmail, String productName, LocalDateTime rentalDate, LocalDateTime returnDate) {
        this.rentId = rentId;
        this.borrowUserEmail = borrowUserEmail;
        this.ownerUserEmail = ownerUserEmail;
        this.productName = productName;
        this.rentalDate = rentalDate;
        this.returnDate = returnDate;
    }
}

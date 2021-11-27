package com.rent.rentshop.rent.owner_user.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * 내 상품을 빌리려는 정보의 응답객체
 */
@Getter
public class MyProductReservationResponse {

    private Long id;
    private String borrowUserEmail;
    private String ownerUserEmail;
    private String productName;
    private LocalDateTime rentalDate;
    private LocalDateTime returnDate;

    @Builder
    public MyProductReservationResponse(Long id, String borrowUserEmail, String ownerUserEmail, String productName, LocalDateTime rentalDate, LocalDateTime returnDate) {
        this.id = id;
        this.borrowUserEmail = borrowUserEmail;
        this.ownerUserEmail = ownerUserEmail;
        this.productName = productName;
        this.rentalDate = rentalDate;
        this.returnDate = returnDate;
    }

}

package com.rent.rentshop.non_rent.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class NonUserRentRequestTest {

    @Test
    @DisplayName("비회원 상품대여 신청 DTO를 생성합니다.")
    public void 비회원_상품대여신청_DTO생성() {
        // GIVEN
        NonUserRentRequest nonUserRentRequest = NonUserRentRequest.builder()
                .rentalDate(LocalDate.of(2021, 12, 15))
                .returnDate(LocalDate.of(2021, 12, 25))
                .name("김형욱")
                .phone("010-0000-0000")
                .build();

        // WHEN
        String nonUserName = "김형욱";
        LocalDate returnedDate = LocalDate.of(2021,12,25);

        // THEN
        assertEquals(nonUserName,nonUserRentRequest.getName());
        assertEquals(returnedDate, nonUserRentRequest.getReturnDate());
    }

}

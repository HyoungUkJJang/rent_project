package com.rent.rentshop.non_rent.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class NonUserRentResponseTest {

    @Test
    @DisplayName("비회원 대여신청 응답 DTO를 생성합니다.")
    public void 비회원_대여신청_응답_DTO생성() {
        // GIVEN
        NonUserRentResponse nonUserRentResponse = NonUserRentResponse.builder()
                .returnDate(LocalDate.of(2021, 12, 15))
                .returnDate(LocalDate.of(2021, 12, 25))
                .name("김형욱")
                .phone("010-0000-0000")
                .build();

        // WHEN
        String name = "김형욱";
        LocalDate returnedDate = LocalDate.of(2021,12,25);

        //THEN
        assertEquals(name,nonUserRentResponse.getName());
        assertEquals(returnedDate, nonUserRentResponse.getReturnDate());

    }

}

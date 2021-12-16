package com.rent.rentshop.non_rent.domain;

import com.rent.rentshop.non_member.domain.NonUser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class NonUserRentTest {

    @Test
    @DisplayName("비회원 대여신청서를 생성합니다.")
    public void 비회원_대여정보_생성() {
        // GIVEN
        NonUserRent nonUserRent = NonUserRent.builder()
                .rentalDate(LocalDate.of(2021, 12, 15))
                .returnDate(LocalDate.of(2021,12,25))
                .nonUser(new NonUser("김형욱","010-0000-0000"))
                .build();

        // WHEN
        LocalDate returndDate = LocalDate.of(2021, 12, 25);
        String nonUserName = "김형욱";

        // THEN
        assertEquals(returndDate, nonUserRent.getReturnDate());
        assertEquals(nonUserName, nonUserRent.getNonUser().getName());
    }

}

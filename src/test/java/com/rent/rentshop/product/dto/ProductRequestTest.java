package com.rent.rentshop.product.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductRequestTest {

    @Test
    @DisplayName("상품등록 요청 DTO 생성")
    public void 상품등록_요청_DTO_생성() {

        //GIVEN
        ProductRequest productRequest = ProductRequest.builder()
                .name("macBook")
                .price(3000)
                .deposit(50000)
                .description("맥북프로 2019 모델입니다.")
                .build();

        //WHEN
        int price = 3000;
        int deposit = 50000;

        //THEN
        assertEquals(price, productRequest.getPrice());
        assertEquals(deposit, productRequest.getDeposit());

    }

}

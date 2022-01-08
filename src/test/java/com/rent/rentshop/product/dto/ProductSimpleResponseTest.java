package com.rent.rentshop.product.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductSimpleResponseTest {

    @Test
    @DisplayName("상품목록 응답 DTO 생성")
    public void 상품목록응답_DTO_생성() throws Exception {

        //GIVEN
        ProductSimpleResponse productSimpleResponse = ProductSimpleResponse.builder()
                .id(1L)
                .name("macBook")
                .price(3000)
                .thumbnailImage("/home/ec-user/serverFileName.jpg")
                .build();

        //WHEN
        String name = "macBook";
        int price = 3000;

        //THEN
        assertEquals(name, productSimpleResponse.getName());
        assertEquals(price, productSimpleResponse.getPrice());

    }

}

package com.rent.rentshop.product.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ProductImageResponseTest {

    @Test
    @DisplayName("상품이미지 응답 DTO 생성")
    public void 상품이미지_응답_DTO_생성() {

        //given
        ProductImageResponse productImageResponse = new ProductImageResponse("macBook",
                UUID.randomUUID().toString());

        //when
        String originalName = "macBook";

        //then
        assertEquals(originalName, productImageResponse.getOriginalFileName());

    }

}

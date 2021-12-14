package com.rent.rentshop.product.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ProductImageTest {

    @Test
    @DisplayName("상품이미지 생성")
    public void 상품이미지_생성() {

        //given
        ProductImage productImage = ProductImage.builder()
                .originalFileName("macBook")
                .serverFileName(UUID.randomUUID().toString())
                .filePath("/home/ec-user")
                .build();

        //when
        String originalName = "macBook";

        //then
        assertEquals(originalName, productImage.getOriginalFileName());

    }

}

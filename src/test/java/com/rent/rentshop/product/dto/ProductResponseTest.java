package com.rent.rentshop.product.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ProductResponseTest {

    @Test
    @DisplayName("상품응답 DTO 생성")
    public void 상품응답_DTO_생성() throws Exception {

        //GIVEN
        List<ProductImageResponse> images = new ArrayList<>();
        images.add(new ProductImageResponse("macBook", UUID.randomUUID().toString()));

        ProductResponse productResponse = ProductResponse.builder()
                .id(1L)
                .name("macBook")
                .price(3000)
                .deposit(50000)
                .description("맥북프로 2019 모델입니다.")
                .images(images)
                .build();

        //WHEN
        int price = 3000;
        String imageName = "macBook";

        //THEN
        assertEquals(price, productResponse.getPrice());
        assertEquals(imageName, productResponse.getImages()
                                 .get(0).getOriginalFileName());

    }

}

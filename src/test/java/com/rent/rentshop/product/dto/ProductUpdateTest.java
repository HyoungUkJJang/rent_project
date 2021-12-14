package com.rent.rentshop.product.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductUpdateTest {

    @Test
    @DisplayName("상품수정 DTO 생성")
    public void 상품수정_DTO_생성() throws Exception {

        //GIVEN
        ProductUpdate productUpdate = ProductUpdate.builder()
                .name("updateMacBook")
                .price(5000)
                .deposit(100000)
                .description("상품설명 수정")
                .build();

        //WHEN
        String name = "updateMacBook";
        int updatePrice = 5000;

        //THEN
        assertEquals(name, productUpdate.getName());
        assertEquals(updatePrice, productUpdate.getPrice());

    }

}

package com.rent.rentshop.product.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    private Product product;

    @BeforeEach
    void prepare() {
        product = Product.builder()
                .name("product1")
                .price(5000)
                .deposit(10000)
                .description("description1")
                .build();
    }

    @Test
    @DisplayName("상품 생성 테스트")
    public void 상품생성() {

        //GIVEN
        String expectedProductName = "product1";

        //WHEN
        String actualProductName = product.getName();

        //THEN
        assertEquals(expectedProductName, actualProductName);

    }

    @Test
    @DisplayName("상품 수정 테스트")
    public void 상품수정() {

        //GIVEN
        String updateProductName = "updateName";
        String updateDescription = "updateDescription";
        int updateDeposit = 20000;
        int updatePrice = 10000;

        //WHEN
        product.updateProduct(updateProductName, updateDescription,
                updatePrice, updateDeposit);

        //THEN
        assertEquals(product.getName(),updateProductName);

    }

}

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
                .productName("product1")
                .productPrice(5000)
                .deposit(10000)
                .productImg("img1")
                .productDescription("description1")
                .build();
    }

    @Test
    @DisplayName("상품 생성 테스트")
    public void 상품생성() {

        //GIVEN
        String expectedProductName = "product1";

        //WHEN
        String actualProductName = product.getProductName();

        //THEN
        assertEquals(expectedProductName, actualProductName);

    }

    @Test
    @DisplayName("상품 수정 테스트")
    public void 상품수정() {

        //GIVEN
        String updateProductName = "updateName";
        String updateDescription = "updateDescription";
        String updateImg = "updateImg";
        int updateDeposit = 20000;
        int updatePrice = 10000;
        product.updateProduct(updateProductName, updateDescription,
                updatePrice, updateDeposit, updateImg);

        //WHEN
        String resultProductName = product.getProductName();

        //THEN
        assertEquals(updateProductName, resultProductName);

    }


}
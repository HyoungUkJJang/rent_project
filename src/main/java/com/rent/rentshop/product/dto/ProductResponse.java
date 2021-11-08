package com.rent.rentshop.product.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 상품 상세조회 응답
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductResponse {

    private Long productId;
    private String productName;
    private int productPrice;
    private int deposit;
    private String productDescription;
    private String productImg;

    @Builder
    public ProductResponse(Long productId, String productName, int productPrice, int deposit, String productDescription, String productImg) {
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.deposit = deposit;
        this.productDescription = productDescription;
        this.productImg = productImg;
    }

}

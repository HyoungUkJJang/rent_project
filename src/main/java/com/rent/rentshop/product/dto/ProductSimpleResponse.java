package com.rent.rentshop.product.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 상품 리스트 조회 응답
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductSimpleResponse {

    private Long productId;
    private String productName;
    private int productPrice;
    private int deposit;

    @Builder
    public ProductSimpleResponse(Long productId, String productName, int productPrice, int deposit) {
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.deposit = deposit;
    }

}

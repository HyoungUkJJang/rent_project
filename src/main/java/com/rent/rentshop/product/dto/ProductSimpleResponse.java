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

    private Long id;
    private String productName;
    private int productPrice;
    private int deposit;
    private String thumbnailImage;

    @Builder
    public ProductSimpleResponse(Long id, String productName, int productPrice, int deposit, String thumbnailImage) {
        this.id = id;
        this.productName = productName;
        this.productPrice = productPrice;
        this.deposit = deposit;
        this.thumbnailImage = thumbnailImage;
    }

}

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
    private String name;
    private int price;
    private int deposit;
    private String thumbnailImage;

    @Builder
    public ProductSimpleResponse(Long id, String name, int price, int deposit, String thumbnailImage) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.deposit = deposit;
        this.thumbnailImage = thumbnailImage;
    }

}

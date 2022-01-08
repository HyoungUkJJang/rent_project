package com.rent.rentshop.product.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 우리동네 베스트10 상품 리스트를 응답하기 위한 DTO 클래스입니다.
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductBest10Response {

    private Long id;
    private String thumbnailImage;
    private String name;

    @Builder
    public ProductBest10Response(Long id, String thumbnailImage, String name) {
        this.id = id;
        this.thumbnailImage = thumbnailImage;
        this.name = name;
    }

}

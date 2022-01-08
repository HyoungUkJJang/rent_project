package com.rent.rentshop.product.dto;

import com.rent.rentshop.product.domain.Product;
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
    private String thumbnailImage;
    private String city;

    @Builder
    public ProductSimpleResponse(Long id, String name, int price, String thumbnailImage, String city) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.thumbnailImage = thumbnailImage;
        this.city = city;
    }

    public ProductSimpleResponse(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
        this.thumbnailImage = product.getProductImages().get(0).getServerFileName();
        this.city = product.getCity();
    }

}

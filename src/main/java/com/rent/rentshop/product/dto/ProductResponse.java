package com.rent.rentshop.product.dto;

import com.rent.rentshop.member.dto.UserResponse;
import com.rent.rentshop.product.domain.ProductImage;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 상품 상세조회 응답
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductResponse {

    private Long id;
    private String name;
    private int price;
    private int deposit;
    private String description;
    private List<String> tags;
    private List<ProductImageResponse> images;
    private UserResponse.UserSimpleResponse user;

    @Builder
    public ProductResponse(Long id, String name, int price, int deposit, String description,
                           List<String> tags, List<ProductImageResponse> images, UserResponse.UserSimpleResponse user) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.deposit = deposit;
        this.description = description;
        this.tags = tags;
        this.images = images;
        this.user = user;
    }

}

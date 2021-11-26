package com.rent.rentshop.product.dto;

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

    private Long productId;
    private String productName;
    private int productPrice;
    private int deposit;
    private String productDescription;
    private List<ProductImageResponse> productImages;

    @Builder
    public ProductResponse(Long productId, String productName, int productPrice, int deposit, String productDescription, List<ProductImageResponse> productImages) {
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.deposit = deposit;
        this.productDescription = productDescription;
        this.productImages = productImages;
    }

}

package com.rent.rentshop.product.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 상품등록 요청
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductRequest {

    @NotEmpty(message = "상품 이름은 필수입니다.")
    private String name;
    @NotNull(message = "상품 가격은 필수입니다.")
    private int price;
    @NotNull(message = "보증금은 필수입니다.")
    private int deposit;

    private String description;
    private List<MultipartFile> images;

    @Builder
    public ProductRequest(String name, int price, int deposit, String description, List<MultipartFile> images) {
        this.name = name;
        this.price = price;
        this.deposit = deposit;
        this.description = description;
        this.images = images;
    }

}

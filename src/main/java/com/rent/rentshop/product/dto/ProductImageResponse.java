package com.rent.rentshop.product.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductImageResponse {

    private String originalFileName;
    private String serverFileName;

    @Builder
    public ProductImageResponse(String originalFileName, String serverFileName) {
        this.originalFileName = originalFileName;
        this.serverFileName = serverFileName;
    }

}

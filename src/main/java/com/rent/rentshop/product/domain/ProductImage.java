package com.rent.rentshop.product.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class ProductImage {

    @Id
    @GeneratedValue
    @Column(name = "image_id")
    private Long id;
    private String originalFileName;
    private String serverFileName;
    private String filePath;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Builder
    public ProductImage(String originalFileName, String serverFileName, String filePath) {
        this.originalFileName = originalFileName;
        this.serverFileName = serverFileName;
        this.filePath = filePath;
    }

}

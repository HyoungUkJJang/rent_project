package com.rent.rentshop.product.domain;

import com.rent.rentshop.common.BaseTime;
import com.rent.rentshop.member.domain.User;
import com.rent.rentshop.rent.domain.Rent;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 상품의 메인 도메인
 */
@Entity
@Getter
@NoArgsConstructor
public class Product extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    @Column(length = 100)
    private String name;
    @Column(columnDefinition = "TEXT")
    private String description;
    private int price;
    private int deposit;
    private String city;
    private Long hit;

    @OneToMany(mappedBy = "product")
    List<ProductImage> productImages = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "product")
    List<Rent> rents = new ArrayList<>();

    @Builder
    public Product(String name, String description, int price, int deposit, String city, Long hit) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.deposit = deposit;
        this.city = city;
        this.hit = hit;
    }

    /**
     * 상품 정보를 수정합니다.
     * @param name 상품의 수정될 이름
     * @param description 상품의 수정될 설명
     * @param price 상품의 수정될 가격
     * @param deposit 상품의 수정될 보증금
     */
    public void updateProduct(String name, String description, int price, int deposit) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.deposit = deposit;
    }

    public void setProductImages(List<ProductImage> productImages) {
        this.productImages = productImages;
    }

    public void setUser(User user) {
        this.user = user;
    }

}

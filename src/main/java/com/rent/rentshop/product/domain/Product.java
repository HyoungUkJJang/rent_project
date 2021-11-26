package com.rent.rentshop.product.domain;

import com.rent.rentshop.common.BaseTime;
import com.rent.rentshop.member.domain.User;
import com.rent.rentshop.rent.domain.Rent;
import lombok.*;

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
    @GeneratedValue
    @Column(name = "product_id")
    private Long id;

    private String productName;
    private String productDescription;
    private int productPrice;
    private int deposit;

    @OneToMany(mappedBy = "product")
    List<ProductImage> productImages = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "product")
    List<Rent> rents = new ArrayList<>();

    @Builder
    public Product(Long id, String productName, String productDescription, int productPrice, int deposit) {
        this.id = id;
        this.productName = productName;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
        this.deposit = deposit;
    }

    public void updateProduct(String productName, String productDescription, int productPrice, int deposit) {
        this.productName = productName;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
        this.deposit = deposit;
    }

    public void setUser(User user) {
        this.user = user;
    }

}

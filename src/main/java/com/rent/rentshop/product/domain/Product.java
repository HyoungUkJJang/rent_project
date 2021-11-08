package com.rent.rentshop.product.domain;

import com.rent.rentshop.common.BaseTime;
import com.rent.rentshop.member.domain.User;
import lombok.*;

import javax.persistence.*;

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
    private String productImg;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public Product(Long id, String productName, String productDescription, int productPrice, int deposit, String productImg) {
        this.id = id;
        this.productName = productName;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
        this.deposit = deposit;
        this.productImg = productImg;
    }

    public void updateProduct(String productName, String productDescription, int productPrice, int deposit, String productImg) {
        this.productName = productName;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
        this.deposit = deposit;
        this.productImg = productImg;
    }

    public void setUser(User user) {
        this.user = user;
    }

}

package com.rent.rentshop.non_rent.domain;

import com.rent.rentshop.non_member.domain.NonUser;
import com.rent.rentshop.product.domain.Product;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table
@Getter
@NoArgsConstructor
public class NonUserRent {

    @Id
    @GeneratedValue
    private Long id;
    private LocalDateTime rentalDate;
    private LocalDateTime returnDate;

    @Embedded
    private NonUser nonUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @Builder
    public NonUserRent(LocalDateTime rentalDate, LocalDateTime returnDate, NonUser nonUser, Product product) {
        this.rentalDate = rentalDate;
        this.returnDate = returnDate;
        this.nonUser = nonUser;
        this.product = product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

}

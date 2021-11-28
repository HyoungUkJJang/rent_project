package com.rent.rentshop.non_rent.domain;

import com.rent.rentshop.non_member.domain.NonUser;
import com.rent.rentshop.product.domain.Product;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table
@Getter
@NoArgsConstructor
public class NonUserRent {

    @Id
    @GeneratedValue
    private Long id;
    private LocalDate rentalDate;
    private LocalDate returnDate;
    @Embedded
    private NonUser nonUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @Builder
    public NonUserRent(LocalDate rentalDate, LocalDate returnDate, NonUser nonUser, Product product) {
        this.rentalDate = rentalDate;
        this.returnDate = returnDate;
        this.nonUser = nonUser;
        this.product = product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

}

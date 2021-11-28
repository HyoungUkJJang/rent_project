package com.rent.rentshop.non_rent.domain;

import com.rent.rentshop.non_member.domain.NonUser;
import com.rent.rentshop.product.domain.Product;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Entity
@Table
@NoArgsConstructor
public class NonUserRent2 {

    @Id
    @GeneratedValue
    private Long id;
    private LocalDate rentalDate;
    private LocalDate returnDate;
    private String password;
    @Embedded
    private NonUser nonUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @Builder
    public NonUserRent2(LocalDate rentalDate, LocalDate returnDate, String password, NonUser nonUser) {
        this.rentalDate = rentalDate;
        this.returnDate = returnDate;
        this.password = password;
        this.nonUser = nonUser;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

}

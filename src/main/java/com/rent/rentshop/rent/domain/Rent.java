package com.rent.rentshop.rent.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rent.rentshop.member.domain.User;
import com.rent.rentshop.product.domain.Product;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
public class Rent {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private Product product;

    private LocalDateTime rentalDate;
    private LocalDateTime returnDate;

    @Enumerated(EnumType.STRING)
    private RentStatus rentStatus;

    Rent() {};

    @Builder
    public Rent(LocalDateTime rentalDate, LocalDateTime returnDate, RentStatus rentStatus) {
        this.rentalDate = rentalDate;
        this.returnDate = returnDate;
        this.rentStatus = rentStatus;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

}

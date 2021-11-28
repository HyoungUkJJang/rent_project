package com.rent.rentshop.non_rent.service;

import com.rent.rentshop.non_member.domain.NonUser;
import com.rent.rentshop.non_rent.domain.NonUserRent;
import com.rent.rentshop.non_rent.domain.NonUserRent2;
import com.rent.rentshop.non_rent.dto.NonUserRentRequest2;
import com.rent.rentshop.non_rent.repository.NonUserRentRepository2;
import com.rent.rentshop.product.domain.Product;
import com.rent.rentshop.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class NonUserRentServiceImpl2 implements NonUserRentService2 {

    private final NonUserRentRepository2 nonUserRentRepository2;
    private final ProductService productService;

    @Override
    @Transactional
    public NonUserRent2 createRent(NonUserRentRequest2 nonUserRentRequest2, Long productId) {

        Product findProduct = productService.getProduct(productId);

        NonUserRent2 form = NonUserRent2.builder()
                .rentalDate(nonUserRentRequest2.getRentalDate())
                .returnDate(nonUserRentRequest2.getReturnDate())
                .password(nonUserRentRequest2.getPassword())
                .nonUser(new NonUser(nonUserRentRequest2.getName(),
                        nonUserRentRequest2.getPhone()))
                .build();
        form.setProduct(findProduct);

        NonUserRent2 result = nonUserRentRepository2.save(form);
        return result;
    }

}

package com.rent.rentshop.non_rent.service;

import com.rent.rentshop.error.ProductNotFoundException;
import com.rent.rentshop.non_member.domain.NonUser;
import com.rent.rentshop.non_rent.domain.NonUserRent;
import com.rent.rentshop.non_rent.dto.NonUserRentRequest;
import com.rent.rentshop.non_rent.repository.NonUserRentRepository;
import com.rent.rentshop.product.domain.Product;
import com.rent.rentshop.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class NonUserRentServiceImpl implements NonUserRentService {

    private final NonUserRentRepository nonUserRentRepository;
    private final ProductRepository productRepository;

    @Override
    @Transactional
    public NonUserRent createRent(NonUserRentRequest nonUserRentRequest, Long productId) {

        Product findProduct = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException());

        NonUserRent form = NonUserRent.builder()
                .rentalDate(nonUserRentRequest.getRentalDate())
                .returnDate(nonUserRentRequest.getReturnDate())
                .nonUser(new NonUser(nonUserRentRequest.getName(),
                        nonUserRentRequest.getPhone()))
                .build();
        form.setProduct(findProduct);

        NonUserRent result = nonUserRentRepository.save(form);
        return result;
    }

}

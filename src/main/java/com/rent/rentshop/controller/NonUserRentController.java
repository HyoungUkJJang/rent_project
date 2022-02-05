package com.rent.rentshop.controller;

import com.rent.rentshop.non_rent.domain.NonUserRent;
import com.rent.rentshop.non_rent.dto.NonUserRentRequest;
import com.rent.rentshop.non_rent.dto.NonUserRentResponse;
import com.rent.rentshop.non_rent.service.NonUserRentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("rent/products/{productId}/borrow_nonuser")
@RequiredArgsConstructor
@CrossOrigin
public class NonUserRentController {

    private final NonUserRentService nonUserRentService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public NonUserRentResponse createRent(@RequestBody @Valid NonUserRentRequest nonUserRentRequest, @PathVariable Long productId) {
        NonUserRent rent = nonUserRentService.createRent(nonUserRentRequest, productId);
        NonUserRentResponse result = NonUserRentResponse.builder()
                .rentalDate(rent.getRentalDate())
                .returnDate(rent.getReturnDate())
                .name(rent.getNonUser().getName())
                .phone(rent.getNonUser().getPhone())
                .build();

        return result;
    }

}

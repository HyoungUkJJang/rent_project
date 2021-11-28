package com.rent.rentshop.controller;

import com.rent.rentshop.non_rent.domain.NonUserRent2;
import com.rent.rentshop.non_rent.dto.NonUserRentRequest2;
import com.rent.rentshop.non_rent.dto.NonUserRentResponse;
import com.rent.rentshop.non_rent.service.NonUserRentService2;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("rent/nonuser2/borrow")
@RequiredArgsConstructor
@CrossOrigin
public class NonUserRentController2 {

    private final NonUserRentService2 nonUserRentService2;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public NonUserRentResponse createRent(@RequestBody @Valid NonUserRentRequest2 nonUserRentRequest2, @RequestParam Long id) {
        NonUserRent2 rent = nonUserRentService2.createRent(nonUserRentRequest2, id);

        NonUserRentResponse result = NonUserRentResponse.builder()
                .rentalDate(rent.getRentalDate())
                .returnDate(rent.getReturnDate())
                .name(rent.getNonUser().getName())
                .phone(rent.getNonUser().getPhone())
                .build();

        return result;
    }

}

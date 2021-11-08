package com.rent.rentshop.controller;

import com.rent.rentshop.common.ResponseData;
import com.rent.rentshop.rent.domain.Rent;
import com.rent.rentshop.rent.dto.MyRentResponse;
import com.rent.rentshop.rent.dto.RentRequest;
import com.rent.rentshop.rent.dto.RentResponse;
import com.rent.rentshop.rent.service.RentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rent/rental")
@RequiredArgsConstructor
public class RentController {

    private final RentService rentService;

    @PostMapping
    public ResponseData createRental(@RequestParam("userEmail") String userEmail, @RequestParam("productId") Long productId,
                                     @RequestBody @Valid RentRequest rentRequest) {

        Rent rent = rentService.createRent(userEmail, productId, rentRequest);

        RentResponse result = RentResponse.builder()
                .rentId(rent.getId())
                .productName(rent.getProduct().getProductName())
                .rentalDate(rent.getRentalDate())
                .returnDate(rent.getRentalDate())
                .build();

        return new ResponseData(result);

    }

    @GetMapping("/myrental/{userEmail}")
    public ResponseData getMyRental(@PathVariable("userEmail") String userEmail) {
        List<Rent> myRent = rentService.findMyRent(userEmail);
        List<MyRentResponse> result = myRent.stream()
                .map(m -> new MyRentResponse(
                        m.getProduct().getProductName(),
                        m.getProduct().getProductPrice(),
                        m.getProduct().getDeposit(),
                        m.getProduct().getUser().getUserEmail(),
                        m.getRentalDate(),
                        m.getReturnDate()))
                .collect(Collectors.toList());

        for (MyRentResponse myRentResponse : result) {
            System.out.println("myRentResponse = " + myRentResponse);
        }

        return new ResponseData(result);
    }

//    @GetMapping("/reservation/{userEmail}")
//    public ResponseData getMyProductReservation(@PathVariable("userEmail") String userEmail) {
//        List<Rent> myRent = rentService.findMyReservationRent(userEmail);
//        List<MyRentResponse> result = myRent.stream()
//                .map(m -> new MyRentResponse(
//                        m.getProduct().getProductName(),
//                        m.getProduct().getProductPrice(),
//                        m.getProduct().getDeposit(),
//                        m.getProduct().getUser().getUserEmail(),
//                        m.getRentalDate(),
//                        m.getReturnDate()))
//                .collect(Collectors.toList());
//
//        for (MyRentResponse myRentResponse : result) {
//            System.out.println("myRentResponse = " + myRentResponse);
//        }
//
//
//        return new ResponseData(result);
//    }

}

package com.rent.rentshop.controller;

import com.rent.rentshop.common.ResponseData;
import com.rent.rentshop.rent.domain.Rent;
import com.rent.rentshop.rent.dto.*;
import com.rent.rentshop.rent.service.RentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 상품대여에 관한 HTTP 요청을 처리하는 컨트롤러
 */
@RestController
@RequestMapping("/rent/rental")
@RequiredArgsConstructor
public class RentController {

    private final RentService rentService;

    /**
     * 상품 대여를 생성하여 대여 신청을 처리합니다.
     * @param userEmail 빌리는 사람의 아이디
     * @param productId 빌리려는 상품의 아이디
     * @param rentRequest 대여시작, 반납일이 담겨져있는 대여정보
     * @return 상품 대여완료 정보
     */
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

    /**
     * 사용자가 대여신청한 상품들을 조회하여 리턴합니다.
     * @param userEmail 사용자 아이디
     * @return 대여신청된 상품목록
     */
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

    /**
     * 사용자가 자신이 올린 상품에 대여신청 현황을 조회하여 리턴합니다.
     * @param userEmail 사용자 아이디
     * @return 사용자가 올린 상품에 대하여 대여신청을 한 목록
     */
    @GetMapping("/reservation/{userEmail}")
    public ResponseData getMyProductReservation(@PathVariable("userEmail") String userEmail) {

        List<Rent> myReservationRents = rentService.getMyProductRentalUserList(userEmail);
        List<MyProductReservationResponse> result = myReservationRents.stream()
                .map(r -> new MyProductReservationResponse(
                        r.getId(),
                        r.getUser().getUserEmail(),
                        r.getProduct().getUser().getUserEmail(),
                        r.getProduct().getProductName(),
                        r.getRentalDate(),
                        r.getRentalDate()
                ))
                .collect(Collectors.toList());

        return new ResponseData(result);
    }

    /**
     * 상품의 주인이 대여 신청 접수를 완료합니다.
     * @param userEmail 상품의 주인 아이디
     * @param rentId 상품대여 정보의 아이디
     * @return
     */
    @PostMapping("/reservation/{userEmail}/{rentId}")
    public ResponseData rentReservationComplete(@PathVariable("userEmail") String userEmail, @PathVariable("rentId") Long rentId) {

        Rent findRent = rentService.rentComplete(userEmail, rentId);
        RentCompleteResponse result = RentCompleteResponse.builder()
                .rentId(findRent.getId())
                .productName(findRent.getProduct().getProductName())
                .productPrice(findRent.getProduct().getProductPrice())
                .deposit(findRent.getProduct().getDeposit())
                .ownerUserMail(findRent.getProduct().getUser().getUserEmail())
                .borrowUserEmail(findRent.getUser().getUserEmail())
                .rentalDate(findRent.getRentalDate())
                .returnDate(findRent.getReturnDate())
                .build();
        return new ResponseData(result);

    }

}

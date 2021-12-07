package com.rent.rentshop.controller;

import com.rent.rentshop.common.ResponseData;
import com.rent.rentshop.rent.borrow_user.service.BorrowRentService;
import com.rent.rentshop.rent.domain.Rent;
import com.rent.rentshop.rent.borrow_user.dto.MyRentResponse;
import com.rent.rentshop.rent.borrow_user.dto.MyRentReturnResponse;
import com.rent.rentshop.rent.borrow_user.dto.RentRequest;
import com.rent.rentshop.rent.borrow_user.dto.RentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 상품을 대여하는 사용자의 HTTP 요청을 처리하는 컨트롤러
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/rent/borrow")
@CrossOrigin
public class BorrowRentController {

    private final BorrowRentService borrowRentService;

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

        Rent rent = borrowRentService.createRent(userEmail, productId, rentRequest);

        RentResponse result = RentResponse.builder()
                .id(rent.getId())
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
        List<Rent> myRent = borrowRentService.findMyRent(userEmail);
        List<MyRentResponse> result = myRent.stream()
                .map(m -> new MyRentResponse(
                        m.getProduct().getProductName(),
                        m.getProduct().getProductPrice(),
                        m.getProduct().getDeposit(),
                        m.getProduct().getUser().getEmail(),
                        m.getRentalDate(),
                        m.getReturnDate()))
                .collect(Collectors.toList());

        for (MyRentResponse myRentResponse : result) {
            System.out.println("myRentResponse = " + myRentResponse);
        }

        return new ResponseData(result);
    }

    /**
     * 내가 빌렸던 상품을 반납신청 처리를 합니다.
     * @param productId 빌렸던 상품의 아이디
     * @param userEmail 빌린 유저의 이메일
     * @return 빌렸던 상품의 정보
     */
    @PostMapping("/myrental/returned/{productId}/{userEmail}")
    public ResponseData returnedMyRental(@PathVariable("productId") Long productId, @PathVariable("userEmail") String userEmail) {

        Rent returnedMyRental = borrowRentService.returnedMyRental(productId, userEmail);
        MyRentReturnResponse result = MyRentReturnResponse.builder()
                .productName(returnedMyRental.getProduct().getProductName())
                .ownerUserEmail(returnedMyRental.getUser().getEmail())
                .rentStatus(returnedMyRental.getRentStatus())
                .build();

        return new ResponseData(result);
    }
}

package com.rent.rentshop.controller;

import com.rent.rentshop.common.ResponseData;
import com.rent.rentshop.rent.domain.Rent;
import com.rent.rentshop.rent.owner_user.dto.MyProductReservationResponse;
import com.rent.rentshop.rent.owner_user.dto.RentCompleteResponse;
import com.rent.rentshop.rent.owner_user.service.OwnerRentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 상품주인과 관련된 상품대여 HTTP 요청을 처리하는 컨트롤러
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/rent/owner")
public class OwnerRentController {

    private final OwnerRentService ownerRentService;

    /**
     * 사용자가 자신이 올린 상품에 대여신청 현황을 조회하여 리턴합니다.
     * @param userEmail 사용자 아이디
     * @return 사용자가 올린 상품에 대하여 대여신청을 한 목록
     */
    @GetMapping("/reservation/{userEmail}")
    public ResponseData getMyProductReservation(@PathVariable("userEmail") String userEmail) {

        List<Rent> myReservationRents = ownerRentService.getMyProductRentalUserList(userEmail);
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

        Rent findRent = ownerRentService.rentComplete(userEmail, rentId);
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

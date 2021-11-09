package com.rent.rentshop.rent.borrow_user.service;

import com.rent.rentshop.rent.domain.Rent;
import com.rent.rentshop.rent.borrow_user.dto.RentRequest;

import java.util.List;

public interface BorrowRentService {

    Rent createRent(String userEmail, Long productId, RentRequest rentRequest);

    List<Rent> findRents();

    // 내가 신청한 대여 품목
    List<Rent> findMyRent(String userEmail);

    // 내가 빌린 상품 반납신청
    Rent returnedMyRental(Long productId, String userEmail);

}

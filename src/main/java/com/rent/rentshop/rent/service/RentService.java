package com.rent.rentshop.rent.service;

import com.rent.rentshop.rent.domain.Rent;
import com.rent.rentshop.rent.dto.RentRequest;

import java.util.List;

public interface RentService {

    Rent createRent(String userEmail, Long productId, RentRequest rentRequest);

    List<Rent> findRents();

    // 내가 신청한 대여 품목
    List<Rent> findMyRent(String userEmail);

    // 나의 품목을 신청한 현황
    List<Rent> getMyProductRentalUserList(String userEmail);

}

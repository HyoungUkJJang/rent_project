package com.rent.rentshop.rent.owner_user.service;

import com.rent.rentshop.rent.domain.Rent;

import java.util.List;

public interface OwnerRentService {

    // 나의 품목을 신청한 현황
    List<Rent> getMyProductRentalUserList(String userEmail);

    // 렌트신청 접수
    Rent rentComplete(String userId, Long rentId);

}

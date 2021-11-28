package com.rent.rentshop.non_rent.service;

import com.rent.rentshop.non_rent.domain.NonUserRent;
import com.rent.rentshop.non_rent.dto.NonUserRentRequest;

/**
 * 비회원 사용자 대여 서비스
 */
public interface NonUserRentService {
    NonUserRent createRent(NonUserRentRequest nonUserRentRequest, Long productId);

}

package com.rent.rentshop.non_rent.service;

import com.rent.rentshop.non_rent.domain.NonUserRent;
import com.rent.rentshop.non_rent.domain.NonUserRent2;
import com.rent.rentshop.non_rent.dto.NonUserRentRequest;
import com.rent.rentshop.non_rent.dto.NonUserRentRequest2;

/**
 * 비회원 사용자 대여 서비스
 */
public interface NonUserRentService2 {
    NonUserRent2 createRent(NonUserRentRequest2 nonUserRentRequest2, Long productId);

}

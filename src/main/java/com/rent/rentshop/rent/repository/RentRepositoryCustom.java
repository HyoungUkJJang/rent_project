package com.rent.rentshop.rent.repository;

import com.rent.rentshop.rent.domain.Rent;

import java.util.List;

public interface RentRepositoryCustom {

    List<Rent> getMyApplyRentList(Long userId);

}

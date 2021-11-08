package com.rent.rentshop.rent.service;

import com.rent.rentshop.rent.domain.Rent;
import com.rent.rentshop.rent.dto.RentRequest;

import java.util.List;

public interface RentService {

    Rent createRent(String userId, Long productId, RentRequest rentRequest);

    List<Rent> findMyRent(String userId);

    List<Rent> findRents();

 //   List<Rent> findMyReservationRent(String userEmail);

}

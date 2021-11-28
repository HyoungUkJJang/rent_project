package com.rent.rentshop.non_rent.repository;

import com.rent.rentshop.non_rent.domain.NonUserRent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NonUserRentRepository extends JpaRepository<NonUserRent, Long> {
}

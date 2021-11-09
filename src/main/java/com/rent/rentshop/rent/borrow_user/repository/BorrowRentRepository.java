package com.rent.rentshop.rent.borrow_user.repository;

import com.rent.rentshop.rent.domain.Rent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BorrowRentRepository extends JpaRepository<Rent, Long>, BorrowRentRepositoryCustom {
}

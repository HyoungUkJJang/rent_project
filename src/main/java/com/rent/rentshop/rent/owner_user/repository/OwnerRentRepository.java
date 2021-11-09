package com.rent.rentshop.rent.owner_user.repository;

import com.rent.rentshop.rent.domain.Rent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerRentRepository extends JpaRepository<Rent, Long>, OwnerRentRepositoryCustom {
}

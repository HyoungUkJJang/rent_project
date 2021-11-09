package com.rent.rentshop.rent.service;

import com.rent.rentshop.error.ProductNotFoundException;
import com.rent.rentshop.error.RentNotFoundException;
import com.rent.rentshop.error.UserNotFoundException;
import com.rent.rentshop.member.domain.User;
import com.rent.rentshop.member.repository.UserRepository;
import com.rent.rentshop.product.domain.Product;
import com.rent.rentshop.product.repository.ProductRepository;
import com.rent.rentshop.rent.domain.Rent;
import com.rent.rentshop.rent.domain.RentStatus;
import com.rent.rentshop.rent.dto.RentRequest;
import com.rent.rentshop.rent.repository.RentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RentServiceImpl implements RentService{

    private final RentRepository rentRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public Rent createRent(String userEmail, Long productId, RentRequest rentRequest) {

        User findUser = userRepository.findByUserEmail(userEmail)
                .orElseThrow(() -> new UserNotFoundException());

        Product findProduct = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException());

        Rent rent = Rent.builder()
                .rentalDate(rentRequest.getRentalDate())
                .returnDate(rentRequest.getReturnDate())
                .rentStatus(RentStatus.WAIT)
                .build();
        rent.setUser(findUser);
        rent.setProduct(findProduct);

        return rentRepository.save(rent);

    }

    @Override
    public List<Rent> findMyRent(String userEmail) {

        User findUser = userRepository.findByUserEmail(userEmail).orElseThrow(() -> new UserNotFoundException());

        List<Rent> findMyRents = rentRepository.getMyApplyRentList(findUser.getId());
        return findMyRents;
    }

    @Override
    @Transactional
    public Rent returnedMyRental(Long productId, String userEmail) {

        User findUser = userRepository.findByUserEmail(userEmail).orElseThrow(() -> new UserNotFoundException());

        Rent result = rentRepository.returnedMyRental(productId, findUser.getId());
        result.rentReturnWait();

        return result;

    }

    @Override
    public List<Rent> getMyProductRentalUserList(String userEmail) {

        User findUser = userRepository.findByUserEmail(userEmail).orElseThrow(() -> new UserNotFoundException());

        List<Rent> result = rentRepository.getMyProductRentalUserList(findUser.getId());
        return result;

    }

    @Override
    @Transactional
    public Rent rentComplete(String userId, Long rentId) {
        Rent findRent = rentRepository.findById(rentId).orElseThrow(() -> new RentNotFoundException());
        findRent.rentComplete();

        return findRent;
    }

    @Override
    public List<Rent> findRents() {
        return rentRepository.findAll();
    }
}

package com.rent.rentshop.rent.borrow_user.service;

import com.rent.rentshop.error.ProductNotFoundException;
import com.rent.rentshop.error.UserNotFoundException;
import com.rent.rentshop.member.domain.User;
import com.rent.rentshop.member.repository.UserRepository;
import com.rent.rentshop.product.domain.Product;
import com.rent.rentshop.product.repository.ProductRepository;
import com.rent.rentshop.rent.borrow_user.repository.BorrowRentRepository;
import com.rent.rentshop.rent.domain.Rent;
import com.rent.rentshop.rent.domain.RentStatus;
import com.rent.rentshop.rent.borrow_user.dto.RentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BorrowRentServiceImpl implements BorrowRentService {

    private final BorrowRentRepository borrowRentRepository;
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

        return borrowRentRepository.save(rent);

    }

    @Override
    @Transactional
    public Rent returnedMyRental(Long productId, String userEmail) {

        User findUser = userRepository.findByUserEmail(userEmail).orElseThrow(() -> new UserNotFoundException());

        Rent result = borrowRentRepository.returnedMyRental(productId, findUser.getId());
        result.rentReturnWait();

        return result;

    }

    @Override
    public List<Rent> findMyRent(String userEmail) {

        User findUser = userRepository.findByUserEmail(userEmail).orElseThrow(() -> new UserNotFoundException());

        List<Rent> findMyRents = borrowRentRepository.getMyApplyRentList(findUser.getId());
        return findMyRents;
    }

    @Override
    public List<Rent> findRents() {
        return borrowRentRepository.findAll();
    }

}

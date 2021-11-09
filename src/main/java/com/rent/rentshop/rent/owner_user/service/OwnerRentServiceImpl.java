package com.rent.rentshop.rent.owner_user.service;

import com.rent.rentshop.error.RentNotFoundException;
import com.rent.rentshop.error.UserNotFoundException;
import com.rent.rentshop.member.domain.User;
import com.rent.rentshop.member.repository.UserRepository;
import com.rent.rentshop.product.repository.ProductRepository;
import com.rent.rentshop.rent.domain.Rent;
import com.rent.rentshop.rent.owner_user.repository.OwnerRentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OwnerRentServiceImpl implements OwnerRentService{

    private final OwnerRentRepository ownerRentRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    @Override
    public List<Rent> getMyProductRentalUserList(String userEmail) {

        User findUser = userRepository.findByUserEmail(userEmail).orElseThrow(() -> new UserNotFoundException());

        List<Rent> result = ownerRentRepository.getMyProductRentalUserList(findUser.getId());
        return result;

    }

    @Override
    @Transactional
    public Rent rentComplete(String userId, Long rentId) {
        Rent findRent = ownerRentRepository.findById(rentId).orElseThrow(() -> new RentNotFoundException());
        findRent.rentComplete();

        return findRent;
    }

    @Override
    @Transactional
    public Rent returnedComplete(Long rentId) {
        Rent findRent = ownerRentRepository.findById(rentId).orElseThrow(() -> new RentNotFoundException());
        findRent.returnComplete();

        return findRent;
    }

}

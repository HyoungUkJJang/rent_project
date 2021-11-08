package com.rent.rentshop.member.service;

import com.rent.rentshop.error.UserNotFoundException;
import com.rent.rentshop.member.domain.User;
import com.rent.rentshop.member.dto.UserUpdate;
import com.rent.rentshop.member.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private  PasswordEncoder passwordEncoder;

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(String userEmail) {
        return userRepository.findByUserEmail(userEmail)
                .orElseThrow(() -> new UserNotFoundException());
    }

    @Override
    @Transactional
    public User join(User form) {
        form.createPassword(form.getPassword(), passwordEncoder);
        return userRepository.save(form);
    }

    @Override
    public boolean userEmailCheck(String userEmail) {
        return userRepository.existsByUserEmail(userEmail);
    }

    @Override
    @Transactional
    public void userUpdate(String userEmail, UserUpdate form) {

        User user = userRepository.findByUserEmail(userEmail)
                .orElseThrow(() -> new UserNotFoundException());

        user.updateUser(form.getUserEmail(),
                form.getUserPhone(), form.getRoadAddress(), form.getDetailAddress());
        user.createPassword(form.getPassword(), passwordEncoder);

    }

    @Override
    @Transactional
    public void userDelete(String userEmail) {

        User result = userRepository.findByUserEmail(userEmail)
                .orElseThrow(() -> new UserNotFoundException());

        userRepository.delete(result);

    }

}

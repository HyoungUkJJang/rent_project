package com.rent.rentshop.rent.service;

import com.rent.rentshop.error.ProductNotFoundException;
import com.rent.rentshop.error.UserNotFoundException;
import com.rent.rentshop.member.domain.Address;
import com.rent.rentshop.member.domain.User;
import com.rent.rentshop.member.repository.UserRepository;
import com.rent.rentshop.member.service.UserService;
import com.rent.rentshop.member.service.UserServiceImpl;
import com.rent.rentshop.product.domain.Product;
import com.rent.rentshop.product.repository.ProductRepository;
import com.rent.rentshop.product.service.ProductService;
import com.rent.rentshop.product.service.ProductServiceImpl;
import com.rent.rentshop.rent.domain.Rent;
import com.rent.rentshop.rent.domain.RentStatus;
import com.rent.rentshop.rent.dto.RentRequest;
import com.rent.rentshop.rent.repository.RentRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Nested
@DisplayName("RentService 클래스")
class RentServiceImplTest {

    private RentService rentService;
    private UserService userService;
    private ProductService productService;

    @Autowired
    RentRepository rentRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ProductRepository productRepository;

    @BeforeEach
    void init() {
        userService = new UserServiceImpl(userRepository);
        productService = new ProductServiceImpl(productRepository, userRepository);
        rentService = new RentServiceImpl(rentRepository, productRepository, userRepository);
    }

    @Nested
    @DisplayName("createRent 메소드는")
    class Describe_createRent {

        @Nested
        @DisplayName("대여할 물건이 있을 경우에")
        class Context_exist_rentalProduct {

            String userEmail;
            Long productId;
            RentRequest rentRequest;

            @BeforeEach
            void prepare() {

                User joinUser = userService.join(createUser());
                User rentalUser = userService.join(createRentalUser());
                userEmail = rentalUser.getUserEmail();
                Product registerProduct = productService.register(createProduct(), joinUser.getUserEmail());
                productId = registerProduct.getId();

                rentRequest = RentRequest.builder()
                        .rentalDate(LocalDateTime.now())
                        .returnDate(LocalDateTime.of(2021, 11, 5, 17, 0))
                        .build();
            }

            @Test
            @DisplayName("대여 시작일, 대여 반납일을 입력받아 대여를 생성한다.")
            void It_save_createRant() {
                Rent rent = rentService.createRent(userEmail, productId, rentRequest);
                assertEquals(rent.getReturnDate(),rentRequest.getReturnDate());
                assertEquals(rent.getRentStatus(), RentStatus.WAIT);
            }

        }

        @Nested
        @DisplayName("대여할 물건을 찾을 수 없을 경우에")
        class Context_not_exist_product {

            String userId;
            Long invalidProductId;
            RentRequest rentRequest;

            @BeforeEach
            void prepare() {
                userService.join(createUser());
                User rentalUser = userService.join(createRentalUser());
                userId = rentalUser.getUserEmail();

                productRepository.findAll().clear();
                invalidProductId = 9999L;

                rentRequest = RentRequest.builder()
                        .rentalDate(LocalDateTime.now())
                        .returnDate(LocalDateTime.of(2021, 11, 5, 17, 0))
                        .build();
            }

            @Test
            @DisplayName("상품을 찾을 수 없다는 ProductNotFoundException 예외를 던진다.")
            void It_return_productNotFoundException() {
                assertThatThrownBy(() -> rentService.createRent(userId, invalidProductId, rentRequest))
                        .isInstanceOf(ProductNotFoundException.class);
            }


        }

        @Nested
        @DisplayName("대여하는 사용자의 아이디를 찾을 수 없을 경우에")
        class Context_not_exist_user {

            String invalidUserEmail;
            Long productIdEmail;
            RentRequest rentRequest;

            @BeforeEach
            void prepare() {
                User joinUser = userService.join(createUser());
                User rentalUser = userService.join(createRentalUser());
                invalidUserEmail = rentalUser.getUserEmail() + "Invalid";

                Product registerProduct = productService.register(createProduct(), joinUser.getUserEmail());
                productIdEmail = registerProduct.getId();

                rentRequest = RentRequest.builder()
                        .rentalDate(LocalDateTime.now())
                        .returnDate(LocalDateTime.of(2021, 11, 5, 17, 0))
                        .build();
            }

            @Test
            @DisplayName("사용자를 찾을 수 없다는 UserNotFoundException 예외를 던진다.")
            void It_return_userNotFoundException() {
                assertThatThrownBy(() -> rentService.createRent(invalidUserEmail, productIdEmail, rentRequest))
                        .isInstanceOf(UserNotFoundException.class);
            }

        }
    }

    private User createUser() {
        User user = User.builder()
                .userEmail("mail@mail")
                .password("12345")
                .userName("name1")
                .userPhone("010")
                .userBirth("1996")
                .userAddress(new Address("road", "detail"))
                .build();
        return user;
    }

    private User createRentalUser() {
        User user = User.builder()
                .userEmail("rental@mail")
                .password("12345")
                .userName("rental")
                .userPhone("010")
                .userBirth("1996")
                .userAddress(new Address("road", "detail"))
                .build();
        return user;
    }

    private Product createProduct() {
        return Product.builder()
                .productName("name1")
                .productPrice(1000)
                .deposit(15000)
                .productDescription("description")
                .productImg("img1")
                .build();
    }

}
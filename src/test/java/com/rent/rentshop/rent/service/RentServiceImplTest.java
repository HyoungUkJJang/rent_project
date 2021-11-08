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
import java.util.List;

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

                rentRequest = createRentRequest();
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

    @Nested
    @DisplayName("findMyRent 메소드는")
    class Describe_findMyRent{

        @Nested
        @DisplayName("사용자가 대여신청한 상품들이 있을 경우에")
        class Context_exist_RentProduct {

            String rentalUserEmail;
            Long productId;
            RentRequest rentRequest;

            @BeforeEach
            void prepare() {
                User joinUser = userService.join(createUser());
                User rentalUser = userService.join(createRentalUser());
                rentalUserEmail = rentalUser.getUserEmail();

                Product registerProduct = productService.register(createProduct(), joinUser.getUserEmail());
                productId = registerProduct.getId();

                rentRequest = createRentRequest();

                rentService.createRent(rentalUserEmail, productId, rentRequest);
            }

            @Test
            @DisplayName("대여 신청한 상품들을 조회하여 리턴합니다.")
            void It_return_rentProductList() {

                List<Rent> result = rentService.findMyRent(rentalUserEmail);
                assertThat(result).hasSize(1);

            }

        }


    }

    @Nested
    @DisplayName("getMyProductRentalUserList 메소드는")
    class Describe_getMyProductRentalUserList {

        @Nested
        @DisplayName("내가 올린 상품에 대여를 신청한 사용자가 있다면")
        class Context_exist_myProduct_rentApply {

            String myEmail;

            @BeforeEach
            void prepare() {
                User joinUser = userService.join(createUser());
                User rentalUser = userService.join(createRentalUser());
                myEmail = joinUser.getUserEmail();

                Product registerProduct = productService.register(createProduct(), joinUser.getUserEmail());
                RentRequest rentRequest = createRentRequest();

                rentService.createRent(rentalUser.getUserEmail(), registerProduct.getId(), rentRequest);

            }

            @Test
            @DisplayName("상품대여 정보와 대여자 정보를 리턴합니다.")
            void It_return_rent() {
                List<Rent> result = rentService.getMyProductRentalUserList(myEmail);
                assertThat(result).hasSize(1);
                assertThat(result.get(0).getProduct().getUser().getUserEmail())
                        .isEqualTo(myEmail);
            }

        }

    }

    @Nested
    @DisplayName("rentComplete 메소드는")
    class Describe_rentComplete {

        @Nested
        @DisplayName("내가 올린 상품에 대여신청을 승인할 경우")
        class Context_rentProduct_apply {

            Long rentId;
            String myEmail;

            @BeforeEach
            void prepare() {
                User joinUser = userService.join(createUser());
                User rentalUser = userService.join(createRentalUser());
                myEmail = joinUser.getUserEmail();

                Product registerProduct = productService.register(createProduct(), joinUser.getUserEmail());
                RentRequest rentRequest = createRentRequest();

                Rent createRent = rentService.createRent(rentalUser.getUserEmail(), registerProduct.getId(), rentRequest);
                rentId = createRent.getId();
            }

            @Test
            @DisplayName("RentStatus 상태를 RENTAL로 변경한다.")
            void It_update_rentStatus_RENTAL() {
                User findUser = userService.getUser(myEmail);
                Rent result = rentService.rentComplete(findUser.getUserEmail(), rentId);
                assertThat(result.getRentStatus()).isEqualTo(RentStatus.RENTAL);
            }

        }

    }

    private RentRequest createRentRequest() {
        return RentRequest.builder()
                .rentalDate(LocalDateTime.now())
                .returnDate(LocalDateTime.of(2021, 11, 5, 17, 0))
                .build();
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
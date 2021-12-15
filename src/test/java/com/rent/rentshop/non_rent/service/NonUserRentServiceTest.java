package com.rent.rentshop.non_rent.service;

import com.rent.rentshop.error.ProductNotFoundException;
import com.rent.rentshop.member.domain.Address;
import com.rent.rentshop.member.domain.User;
import com.rent.rentshop.member.repository.UserRepository;
import com.rent.rentshop.non_member.domain.NonUser;
import com.rent.rentshop.non_rent.domain.NonUserRent;
import com.rent.rentshop.non_rent.dto.NonUserRentRequest;
import com.rent.rentshop.non_rent.repository.NonUserRentRepository;
import com.rent.rentshop.product.domain.Product;
import com.rent.rentshop.product.repository.ProductRepository;
import com.rent.rentshop.product.service.ProductService;
import com.rent.rentshop.product.service.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@DisplayName("NonUserRentService 클래스는")
class NonUserRentServiceTest {

    private NonUserRentService nonUserRentService;
    private ProductService productService;
    private UserRepository userRepository;
    private ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        productRepository = Mockito.mock(ProductRepository.class);
        userRepository = Mockito.mock(UserRepository.class);
        NonUserRentRepository nonUserRentRepository = Mockito.mock(NonUserRentRepository.class);

        productService = new ProductServiceImpl(productRepository, userRepository);
        nonUserRentService = new NonUserRentServiceImpl(nonUserRentRepository, productService);
    }

    @Nested
    @DisplayName("createRent 메서드는")
    class Describe_createRent {

        @Nested
        @DisplayName("비회원인 사용자가 상품 대여신청을 할 경우")
        class Context_exist_nonUser_rent {

            User user;
            Product product;
            Product resultProduct;
            NonUserRent nonUserRent = createNonUserRent();


            @BeforeEach
            void prepare() {
                user =  userRepository.save(createUser());
                product = createProduct();
                given(productRepository.save(product)).willReturn(product);
                given(productRepository.findById(1L)).willReturn(Optional.of(product));
                given(productService.getProduct(1L)).willReturn(product);

                given(nonUserRentService
                        .createRent(
                                createNonUserRentRequest(),
                                1L))
                        .willReturn(nonUserRent);
            }

            @Test
            @DisplayName("사용자 기본정보와 대여신청날짜 반납날짜를 입력받아 저장소에 저장합니다.")
            void It_save_nonUserRentRequest() {
                Product product = productRepository.findById(1L).get();
                System.out.println(product);

                NonUserRent nonUserRentResult = nonUserRentService.createRent(createNonUserRentRequest(), 1L);
                System.out.println(nonUserRentResult);
               assertEquals(nonUserRent.getRentalDate(), nonUserRentResult.getRentalDate());
            }

        }

    }

    /**
     * 비회원 대여신청 테스트 도메인을 생성합니다.
     * @return 테스트 대여신청 객체
     */
    private NonUserRentRequest createNonUserRentRequest() {
        return NonUserRentRequest.builder()
                .rentalDate(LocalDate.of(2021, 12, 15))
                .returnDate(LocalDate.of(2021, 12, 25))
                .name("김형욱")
                .phone("010-0000-0000")
                .build();
    }

    /**
     * 비회원 대여신청 테스트 도메인을 생성합니다.
     * @return 테스트 대여신청 객체
     */
     private NonUserRent createNonUserRent() {
         return NonUserRent.builder()
                 .nonUser(new NonUser("김형욱", "010-0000-0000"))
                 .rentalDate(LocalDate.of(2021, 12, 15))
                 .returnDate(LocalDate.of(2021, 12, 25))
                 .build();
     }

    /**
     * 테스트 상품을 생성합니다.
     * @return 테스트 상품 객체
     */
    private Product createProduct() {
        return Product.builder()
                .id(1L)
                .name("productName1")
                .price(1000)
                .deposit(15000)
                .description("description")
                .build();
    }

    /**
     * 테스트 유저를 생성합니다.
     * @return 테스트 유저 객체
     */
    private User createUser() {
        return User.builder()
                .email("mail@mail.com")
                .password("12345")
                .name("hyounguk")
                .phone("010")
                .birth("1996")
                .bankName("kakaoBank")
                .account("123-123")
                .userAddress(new Address("road", "detail"))
                .build();
    }

}
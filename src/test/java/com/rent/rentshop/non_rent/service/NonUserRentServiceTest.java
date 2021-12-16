package com.rent.rentshop.non_rent.service;

import com.rent.rentshop.member.domain.Address;
import com.rent.rentshop.member.domain.User;
import com.rent.rentshop.non_member.domain.NonUser;
import com.rent.rentshop.non_rent.domain.NonUserRent;
import com.rent.rentshop.non_rent.dto.NonUserRentRequest;
import com.rent.rentshop.non_rent.repository.NonUserRentRepository;
import com.rent.rentshop.product.domain.Product;
import com.rent.rentshop.product.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@DisplayName("NonUserRentService 클래스는")
@ExtendWith(MockitoExtension.class)
class NonUserRentServiceTest {

    @Mock
    ProductRepository productRepository;
    @Mock
    NonUserRentRepository nonUserRentRepository;
    @InjectMocks
    private NonUserRentServiceImpl nonUserRentService;

    @Nested
    @DisplayName("createRent 메서드는")
    class Describe_createRent {

        @Nested
        @DisplayName("비회원인 사용자가 상품 대여신청을 할 경우")
        class Context_exist_nonUser_rent {

            Product product;
            NonUserRentRequest nonUserRentRequest;
            NonUserRent nonUserRent;

            @BeforeEach
            void prepare() {
                product = createProduct();
                nonUserRentRequest = createNonUserRentRequest();
                nonUserRent = createNonUserRent();

                given(productRepository.findById(1L)).willReturn(Optional.of(product));
                given(nonUserRentRepository.save(any(NonUserRent.class))).willReturn(nonUserRent);
            }

            @Test
            @DisplayName("사용자 기본정보와 대여신청날짜 반납날짜를 입력받아 저장소에 저장합니다.")
            void It_save_nonUserRentRequest() {
                NonUserRent result = nonUserRentService.createRent(nonUserRentRequest, 1L);
                assertEquals(nonUserRent.getRentalDate(), result.getRentalDate());
            }

        }

    }

    /**
     * 비회원 대여신청 테스트 도메인을 생성합니다.
     *
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
     *
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
     *
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
     *
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
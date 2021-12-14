package com.rent.rentshop.product.service;

import com.rent.rentshop.error.ProductNotFoundException;
import com.rent.rentshop.member.domain.Address;
import com.rent.rentshop.member.domain.User;
import com.rent.rentshop.member.repository.UserRepository;
import com.rent.rentshop.product.domain.Product;
import com.rent.rentshop.product.dto.ProductUpdate;
import com.rent.rentshop.product.repository.ProductRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Pageable;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
@DisplayName("ProductService 클래스")
class ProductServiceImplTest {

    private static final String USER_MAIL = "mail1@mail.com";
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private Pageable pageable;

    @BeforeEach
    void setUp() {
        productService = new ProductServiceImpl(productRepository, userRepository);
    }

//    @Nested
//    @DisplayName("getProducts 메서드는")
//    class Describe_getProducts {
//
//        @Nested
//        @DisplayName("저장소에 상품들이 존재할 경우")
//        class context_exist_products {
//
//            @BeforeEach
//            void prepare() {
//                for (int i = 0; i < 10; i++) {
//
//                }
//            }
//
//        }
//
//    }
}
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

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("ProductService 클래스")
@DataJpaTest
class ProductServiceImplTest {

    private static final String USER_MAIL = "userMail1";
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;


}
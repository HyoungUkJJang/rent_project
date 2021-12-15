package com.rent.rentshop.product.service;

import com.rent.rentshop.member.domain.Address;
import com.rent.rentshop.member.domain.User;
import com.rent.rentshop.member.repository.UserRepository;
import com.rent.rentshop.product.domain.Product;
import com.rent.rentshop.product.dto.ProductUpdate;
import com.rent.rentshop.product.repository.ProductRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.Set;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

/*
 * getProducts [v]
 * getMyProducts []
 * getProduct [v]
 * register [v]
 * update [v]
 * delete [v]
 */
@DataJpaTest
@DisplayName("ProductService 클래스")
class ProductServiceImplTest {

    private String userEmail;
    private ProductService productService;
    private ValidatorFactory factory;
    private Validator validator;

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        productService = new ProductServiceImpl(productRepository, userRepository);
        User user = userRepository.save(createUser());
        userEmail = user.getEmail();
        factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @AfterEach
    void cleanUp() {
        productRepository.deleteAll();
    }

    @Nested
    @DisplayName("getProducts 메서드는")
    class Describe_getProducts {

        @Nested
        @DisplayName("저장소에 상품들이 존재할 경우")
        class Context_exist_products {

            int size=5;
            Slice<Product> products;

            @BeforeEach
            void prepare() {
                for (int i = 1; i <= 10; i++) {
                    Product product = Product.builder()
                            .name("product" + i)
                            .price(1000 * i)
                            .deposit(5000 * i)
                            .description("description" + i)
                            .build();
                    productService.register(product, userEmail);
                }
                Pageable pageable = PageRequest.of(0, size);
                products = productService.getProducts(pageable);
            }

            @Test
            @DisplayName("Slice 타입으로 상품목록을 리턴합니다.")
            void It_return_products() {
                assertThat(products).isNotEmpty();
                assertEquals(5, products.getSize());
            }
        }

        @Nested
        @DisplayName("저장소에 상품들이 존재하지 않을 경우")
        class Context_not_exist_products {

            Slice<Product> products;

            @BeforeEach
            void prepare() {
                productRepository.deleteAll();
                Pageable pageable = PageRequest.of(0, 5);
                products = productService.getProducts(pageable);
            }

            @Test
            @DisplayName("비어있는 상품 리스트를 반환합니다.")
            void It_return_empty_products() {
                assertThat(products).isEmpty();
            }

        }

    }

    @Nested
    @DisplayName("getProduct 메서드는")
    class Describe_getProduct {

        @Nested
        @DisplayName("상품리스트 중 하나를 상세조회 하는 경우에")
        class Context_products_detail {

            Long productId;
            String description = "description";

            @BeforeEach
            void prepare() {
                Product product = productService.register(createProduct(), userEmail);
                productId = product.getId();
            }

            @Test
            @DisplayName("상품의 전체 정보를 조회하여 리턴합니다.")
            void It_return_product_detail() {
                Product findProduct = productService.getProduct(productId);
                assertEquals(description, findProduct.getDescription());
            }

        }

    }

    @Nested
    @DisplayName("register 메서드는")
    class Describe_register {

        @Nested
        @DisplayName("등록할 상품이 있을 경우에")
        class Context_exist_register_product {

            Product product;
            @BeforeEach
            void prepare() {
                product = Product.builder()
                        .name("newProduct1")
                        .price(2000)
                        .deposit(25000)
                        .description("newDescription")
                        .build();
            }

            @Test
            @DisplayName("저장소에 상품을 저장 후 리턴합니다.")
            void It_saved_return_product() {
                Product savedProduct = productService.register(product, userEmail);
                assertEquals(product.getName(), savedProduct.getName());
            }

        }

    }

    @Nested
    @DisplayName("update 메서드는")
    class Describe_update {

        @Nested
        @DisplayName("자신의 상품 중 수정하고 싶은 내용이 있을 경우에")
        class Context_exist_update_product {

            Long productId;
            ProductUpdate updateForm;

            @BeforeEach
            void prepare() {
                Product registerProduct = productService.register(createProduct(), userEmail);
                productId = registerProduct.getId();
                updateForm = ProductUpdate.builder()
                        .name("updateName1")
                        .price(10000)
                        .deposit(300000)
                        .description("updateDescription1")
                        .build();
            }

            @Test
            @DisplayName("상품 아이디와 수정 폼을 전달받아 상품의 내용을 수정한다.")
            void It_void_update_product() {
                productService.update(productId, updateForm);
                Product findProduct = productService.getProduct(productId);

                assertEquals(updateForm.getName(), findProduct.getName());
                assertEquals(updateForm.getPrice(), findProduct.getPrice());
                assertEquals(updateForm.getDescription(), findProduct.getDescription());
                assertEquals(updateForm.getDeposit(), findProduct.getDeposit());

            }

        }

        @Nested
        @DisplayName("상품들의 수정 내용 중 빈 값이 들어올 경우에")
        class Context_blank_update_product {

            Long productId;
            ProductUpdate updateForm;

            @BeforeEach
            void prepare() {
                Product registerProduct = productService.register(createProduct(), userEmail);
                productId = registerProduct.getId();
                updateForm = ProductUpdate.builder()
                        .build();
            }

            @Test
            @DisplayName("벨리데이션 검증을 통해 사용자에게 비어있는 값 정보를 전달한다.")
            void It_return_not_blank_exception() {
                Set<ConstraintViolation<ProductUpdate>> validate = validator.validate(updateForm);
                assertThat(validate).isNotEmpty();
            }

        }
    }

    @Nested
    @DisplayName("delete 메서드는")
    class Describe_delete {

        @Nested
        @DisplayName("자신이 올린 상품들 중 삭제하고 싶은 상품이 있을 경우에")
        class Context_exist_delete_product {

            Long productId;
            @BeforeEach
            void prepare() {
                productRepository.deleteAll();
                Product registerProduct = productService.register(createProduct(), userEmail);
                productId = registerProduct.getId();
            }

            @Test
            @DisplayName("상품의 아이디를 통해 상품을 삭제합니다.")
            void It_delete_product() {
                productService.delete(productId);
                Slice<Product> products = productService.getProducts(PageRequest.of(0, 5));
                assertThat(products).isEmpty();
            }

        }

    }

    /**
     * 테스트 상품을 생성합니다.
     * @return 테스트 상품 객체
     */
    private Product createProduct() {
        return Product.builder()
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

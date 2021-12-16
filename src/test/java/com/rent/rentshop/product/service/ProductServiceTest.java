package com.rent.rentshop.product.service;

import com.rent.rentshop.error.ProductNotFoundException;
import com.rent.rentshop.member.domain.Address;
import com.rent.rentshop.member.domain.User;
import com.rent.rentshop.member.repository.UserRepository;
import com.rent.rentshop.product.domain.Product;
import com.rent.rentshop.product.dto.ProductUpdate;
import com.rent.rentshop.product.repository.ProductRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@DisplayName("ProductService 클래스")
@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    ProductRepository productRepository;
    @Mock
    UserRepository userRepository;
    @InjectMocks
    ProductServiceImpl productService;

    @Nested
    @DisplayName("register 메서드는")
    class Describe_register {

        @Nested
        @DisplayName("유저가 등록할 상품이 있을 경우에")
        class Context_exist_register_product {

            User user = createUser();
            Product product = createProduct();

            @BeforeEach
            void prepare() {
                given(userRepository.findByEmail(anyString())).willReturn(Optional.of(user));
                given(productRepository.save(any(Product.class))).willReturn(product);
            }

            @Test
            @DisplayName("저장소에 유저정보를 포함해 상품 테이블이 저장한다.")
            void It_saved_product() {
                Product registerProduct = productService.register(product, user.getEmail());
                assertThat(product.getName()).isEqualTo(registerProduct.getName());
            }

        }

    }

    @Nested
    @DisplayName("getProduct 메서드는")
    class Describe_getProduct {

        @Nested
        @DisplayName("상품목록 중 하나를 상세 조회하고 싶은 경우")
        class Context_detail_product {

            Long selectId = 1L;
            Product product = createProduct();

            @BeforeEach
            void prepare() {
                given(productRepository.findById(anyLong())).willReturn(Optional.of(product));

            }

            @Test
            @DisplayName("상품의 아이디를 통해 상품의 전체 정보를 조회하여 리턴합니다.")
            void It_return_detail_product() {
                Product findProduct = productService.getProduct(selectId);
                assertThat(product.getName()).isEqualTo(findProduct.getName());
            }

        }

        @Nested
        @DisplayName("상품목록 중 상품의 아이디가 존재하지 않을 경우")
        class Context_not_exist_product {

            Long selectInvalidId = 999L;

            @BeforeEach
            void prepare() {
                given(productRepository.findById(anyLong())).willThrow(new ProductNotFoundException());
            }

            @Test
            @DisplayName("상품을 찾을 수 없다는 ProductNotFoundException 예외를 던진다.")
            void It_return_productNotFoundException() {
                assertThatThrownBy(() -> {
                    productService.getProduct(selectInvalidId);
                }).isInstanceOf(ProductNotFoundException.class)
                        .hasMessage("상품을 찾을 수 없습니다.");
            }

        }

    }

    @Nested
    @DisplayName("update 메서드는")
    class Describe_update {

        @Nested
        @DisplayName("상품중 수정항 내용이 있을 경우에")
        class Context_exist_update_product {

            Long selectId = 1L;
            ProductUpdate updateForm;
            Product product = createProduct();

            @BeforeEach
            void prepare() {
                updateForm = ProductUpdate.builder()
                        .name("updateProduct")
                        .price(2000)
                        .deposit(20000)
                        .description("updateDescription1")
                        .build();
                given(productRepository.findById(anyLong())).willReturn(Optional.of(product));
            }

            @Test
            @DisplayName("상품의 아이디와 수정폼을 통해 상품의 내용을 수정한다.")
            void It_update_product() {
                productService.update(selectId, updateForm);
                assertThat(product.getName()).isEqualTo(updateForm.getName());
            }
        }

    }

    @Nested
    @DisplayName("delete 메서드는")
    class Describe_delete {

        @Nested
        @DisplayName("상품 중 삭제할 상품이 있을 경우에")
        class Context_exist_delete_product {

            Product product = createProduct();
            Long deleteId = 1L;

            @BeforeEach
            void prepare() {
                given(productRepository.findById(anyLong())).willReturn(Optional.of(product));
            }

            @Test
            @DisplayName("상품 아이디를 통해 상품을 삭제한다.")
            void It_delete_product() {
                productService.delete(deleteId);
                verify(productRepository).delete(any(Product.class));
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

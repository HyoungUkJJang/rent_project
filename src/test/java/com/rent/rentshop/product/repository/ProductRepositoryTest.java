package com.rent.rentshop.product.repository;

import com.rent.rentshop.member.repository.UserRepository;
import com.rent.rentshop.product.domain.Product;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

/*
 * save [v]
 * findAll [v]
 * findById [v]
 * delete [v]
 */
@DataJpaTest
@DisplayName("ProductRepository 클래스")
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @AfterEach
    void cleanUp() {
        productRepository.deleteAll();
    }

    @Nested
    @DisplayName("findAll 메서드는")
    class Describe_findAll {

        @Nested
        @DisplayName("저장소에 상품들이 존재할 경우")
        class Context_exist_products {

            int productsSize = 10;

            @BeforeEach
            void prepare() {
                for (int i = 0; i < 10; i++) {
                    productRepository.save(createProduct());
                }
            }

            @Test
            @DisplayName("상품목록 전체를 조회하여 리턴합니다.")
            void It_return_products() {
                List<Product> products = productRepository.findAll();
                assertThat(products).hasSize(productsSize);
            }

        }

        @Nested
        @DisplayName("저장소에 상품들이 존재하지 않을 경우")
        class Context_not_exist_products {

            @BeforeEach
            void prepare() {
                productRepository.deleteAll();
            }

            @Test
            @DisplayName("비어있는 상품목록 리스트를 반환합니다.")
            void It_return_empty_products() {
                List<Product> products = productRepository.findAll();
                assertThat(products).isEmpty();
            }

        }

    }

    @Nested
    @DisplayName("save 메서드는")
    class Describe_save {

        @Nested
        @DisplayName("저장할 상품이 있을 경우에")
        class Context_exist_product {

            Product product;

            @BeforeEach
            void prepare() {
                product = createProduct();
            }

            @Test
            @DisplayName("저장소에 상품을 저장한다.")
            void It_saved_product() {
                Product savedProduct = productRepository.save(product);
                assertEquals(product.getName(), savedProduct.getName());
            }

        }

    }

    @Nested
    @DisplayName("findById 메서드는")
    class Describe_findById {

        @Nested
        @DisplayName("조회할 상품이 있을 경우에")
        class Context_select_product {

            Long selectId;

            @BeforeEach
            void prepare() {
                Product savedProduct = productRepository.save(createProduct());
                selectId = savedProduct.getId();
            }

            @Test
            @DisplayName("저장소에서 해당 상품을 조회해 리턴합니다.")
            void It_return_select_product() {
                Optional<Product> findProduct = productRepository.findById(selectId);
                assertThat(findProduct).isPresent();
            }

        }

        @Nested
        @DisplayName("조회할 상품이 존재하지 않을 경우에")
        class Context_not_exist_product {

            Long selectInvalidId = 999L;
            @BeforeEach
            void prepare() {
                productRepository.deleteAll();
            }

            @Test
            @DisplayName("비어있는 옵셔널을 반환한다.")
            void It_return_noSuchElementException() {
                Optional<Product> selectProduct = productRepository.findById(selectInvalidId);
                assertThat(selectProduct).isEmpty();
            }

        }

    }

    @Nested
    @DisplayName("deleteById 메서드는")
    class Describe_deleteById {

        @Nested
        @DisplayName("삭제할 상품이 존재하는 경우")
        class Context_exist_delete_product {

            Long deleteId;

            @BeforeEach
            void prepare() {
                Product savedProducts = productRepository.save(createProduct());
                deleteId = savedProducts.getId();
            }

            @Test
            @DisplayName("상품의 아이디를 통해 저장소에서 상품을 삭제합니다.")
            void It_delete_productId() {
                productRepository.deleteById(deleteId);
                Optional<Product> deleteProduct = productRepository.findById(deleteId);
                assertThat(deleteProduct).isEmpty();
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

}

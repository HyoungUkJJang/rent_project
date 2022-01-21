package com.rent.rentshop.controller;

import com.rent.rentshop.member.service.LoginService;
import com.rent.rentshop.product.domain.Product;
import com.rent.rentshop.product.domain.ProductImage;
import com.rent.rentshop.product.dto.ProductImageResponse;
import com.rent.rentshop.product.dto.ProductResponse;
import com.rent.rentshop.product.dto.ProductSimpleResponse;
import com.rent.rentshop.product.service.ProductImageService;
import com.rent.rentshop.product.service.ProductService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.*;
import static org.hamcrest.Matchers.containsString;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
@MockBean(JpaMetamodelMappingContext.class)
@DisplayName("ProductController 클래스는")
class ProductControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//    @MockBean
//    private ProductService productService;
//    @MockBean
//    private ProductImageService productImageService;
//    @MockBean
//    private LoginService loginService;

//    @Nested
//    @DisplayName("getProducts 메서드는")
//    class Describe_getProducts {
//
//        @Nested
//        @DisplayName("상품목록 조회 GET요청 시 저장소에 상품들이 존재한다면")
//        class Context_exist_repository_products {
//
//            Slice<ProductSimpleResponse> result;
//
//            @BeforeEach
//            void prepare() {
//                List<ProductSimpleResponse> products = new ArrayList<>();
//                for (int i = 1; i <= 10; i++) {
//                    ProductSimpleResponse productSimpleResponse = ProductSimpleResponse.builder()
//                            .name("productName" + i)
//                            .price(500 * i)
//                            .thumbnailImage("image" + i)
//                            .build();
//                    products.add(productSimpleResponse);
//                }
//                PageRequest pageRequest = PageRequest.of(0,5);
//                result = new SliceImpl<>(products, pageRequest, true);
//
//                given(productService.getProducts(pageRequest)).willReturn(result);
//            }
//
//            @Test
//            @DisplayName("상품목록 응답 DTO 리스트를 반환하고 200 상태코드로 응답한다.")
//            void It_return_productSimpleResponse_status_200() throws Exception {
//
//                MockHttpServletResponse response = mockMvc.perform(get("/rent/products")
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .accept(MediaType.APPLICATION_JSON)
//                                .param("page", "0")
//                                .param("size", "5")
//                        ).andReturn().getResponse();
//
//                assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
//                response.getContentAsString().contains("productName1");
//
//            }
//
//        }
//
//    }
//
//    @Nested
//    @DisplayName("getProduct 메서드는")
//    class Describe_getProduct {
//
//        @Nested
//        @DisplayName("상품목록중 하나를 상세조회하는 GET 요청이 들어온 경우")
//        class Context_detail_product {
//
//            @BeforeEach
//            void prepare() {
//                Product product = Product.builder()
//                        .name("productName1")
//                        .price(1000)
//                        .deposit(10000)
//                        .description("description1")
//                        .build();
//                List<ProductImage> productImages = new ArrayList<>();
//                productImages.add(new ProductImage("original1", "server1", "path"));
//                product.setProductImages(productImages);
//
//                given(productService.getProduct(anyLong())).willReturn(product);
//            }
//
//            @Test
//            @DisplayName("상품을 조회하여 DTO 반환 후 200상태코드를 반환합니다.")
//            void It_return_product_status_200() throws Exception {
//
//                mockMvc.perform(get("/rent/products/1")
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .accept(MediaType.APPLICATION_JSON))
//                        .andExpect(content().string(containsString("productName1")))
//                        .andExpect(status().isOk());
//
//            }
//        }
//
//
//    }

}

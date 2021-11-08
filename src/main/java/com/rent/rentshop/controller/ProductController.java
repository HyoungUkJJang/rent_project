package com.rent.rentshop.controller;

import com.rent.rentshop.common.ResponseData;
import com.rent.rentshop.product.domain.Product;
import com.rent.rentshop.product.dto.*;
import com.rent.rentshop.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 상품과 관련된 HTTP 요청을 처리하는 클래스
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/rent/products")
public class ProductController {

    private final ProductService productService;

    /**
     * 상품 전체를 조회하여 상품목록을 반환 후 200 상태코드를 반환합니다.
     * @return 상품리스트 응답 도메인
     */
    @GetMapping
    public ResponseData getProducts() {

        List<ProductSimpleResponse> products = productService.getProducts()
                .stream()
                .map(p -> new ProductSimpleResponse(
                        p.getId(),
                        p.getProductName(),
                        p.getProductPrice(),
                        p.getDeposit())
                ).collect(Collectors.toList());

        return new ResponseData(products);

    }

    /**
     * 상품을 상세조회하여 상품 상세정보를 반환 후 200 상태코드를 반환합니다.
     * @param id 조회할 상품의 아이디
     * @return 상품 상세조회 응답 도메인
     */
    @GetMapping("/{id}")
    public ResponseData getProduct(@PathVariable("id") Long id) {

        Product findProduct = productService.getProduct(id);
        ProductResponse productResponseDto = ProductResponse.builder()
                .productId(findProduct.getId())
                .productName(findProduct.getProductName())
                .productPrice(findProduct.getProductPrice())
                .deposit(findProduct.getDeposit())
                .productDescription(findProduct.getProductDescription())
                .productImg(findProduct.getProductImg())
                .build();

        return new ResponseData(productResponseDto);

    }

    /**
     * 상품을 등록하고 등록된 상품정보와 201 상태코드를 반환합니다.
     * @param form 상품 정보
     * @return 등록된 상품
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseData register(@RequestBody @Valid ProductRequest form, @RequestHeader(name = "userEmail")String userEmail) {

        Product product = Product.builder()
                .productName(form.getProductName())
                .productDescription(form.getProductDescription())
                .productPrice(form.getProductPrice())
                .deposit(form.getDeposit())
                .productImg(form.getProductImg())
                .build();

        Product result = productService.register(product,userEmail);

        ProductResponse responseProduct = ProductResponse.builder()
                .productId(result.getId())
                .productName(result.getProductName())
                .productPrice(result.getProductPrice())
                .deposit(result.getDeposit())
                .productDescription(result.getProductDescription())
                .productImg(result.getProductImg())
                .build();

        return new ResponseData(responseProduct);
    }

    /**
     * 상품을 수정하고 200 상태코드를 반환합니다.
     * @param productId 수정할 상품의 아이디
     * @param productUpdateForm 수정할 상품의 정보
     */
    @RequestMapping(method = {RequestMethod.PATCH, RequestMethod.PUT}, value = "/{id}")
    public void update(@PathVariable("id") Long productId, @RequestBody @Valid ProductUpdate productUpdateForm) {

        productService.update(productId,productUpdateForm);

    }

    /**
     * 상품을 삭제합니다.
     * @param id 삭제할 상품의 아이디
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {

        productService.delete(id);

    }

}

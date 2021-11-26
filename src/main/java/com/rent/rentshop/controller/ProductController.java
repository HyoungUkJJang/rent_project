package com.rent.rentshop.controller;

import com.rent.rentshop.common.ResponseData;
import com.rent.rentshop.product.domain.Product;
import com.rent.rentshop.product.domain.ProductImage;
import com.rent.rentshop.product.dto.*;
import com.rent.rentshop.product.file.ProductFileStore;
import com.rent.rentshop.product.service.ProductImageService;
import com.rent.rentshop.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
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
    private final ProductImageService productImageService;

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
                        p.getDeposit(),
                        p.getProductImages().get(0).getServerFileName())
                ).collect(Collectors.toList());

        return new ResponseData(products);

    }

    /**
     * 사용자가 자신이 올린 상품목록 조회 요청을 처리하여 200 상태코드를 반환합니다.
     * @param userEmail 사용자 이메일
     * @return
     */
    @GetMapping("/myproduct/{userEmail}")
    public ResponseData getMyProductList(@PathVariable("userEmail") String userEmail) {

        List<Product> myProducts = productService.getMyProducts(userEmail);
        List<ProductSimpleResponse> result = myProducts.stream()
                .map(r -> new ProductSimpleResponse(
                        r.getId(),
                        r.getProductName(),
                        r.getProductPrice(),
                        r.getDeposit(),
                        r.getProductImages().get(0).getServerFileName()
                ))
                .collect(Collectors.toList());

        return new ResponseData(result);

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
                .productImages(
                        imageResponsesConverter(findProduct.getProductImages())
                )
                .build();

        return new ResponseData(productResponseDto);

    }

    /**
     * 상품을 등록하고 등록된 상품정보와 201 상태코드를 반환합니다.
     * @param form 상품 정보
     * @return 등록된 상품
     */
    @PostMapping("/{userEmail}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseData register(@Valid ProductRequest form, @PathVariable(name = "userEmail")String userEmail) throws IOException {

        Product product = Product.builder()
                .productName(form.getProductName())
                .productDescription(form.getProductDescription())
                .productPrice(form.getProductPrice())
                .deposit(form.getDeposit())
                .build();
        Product result = productService.register(product,userEmail);

        List<ProductImage> images = productImageService.save(form.getProductImages(), result);
        List<ProductImageResponse> imageResult = imageResponsesConverter(images);

        ProductResponse responseProduct = ProductResponse.builder()
                .productId(result.getId())
                .productName(result.getProductName())
                .productPrice(result.getProductPrice())
                .deposit(result.getDeposit())
                .productDescription(result.getProductDescription())
                .productImages(imageResult)
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


    /**
     * 조회용 이미지 리스트로 변환합니다.
     * @param productImages
     * @return 조회용 이미지 리스트
     */
    private List<ProductImageResponse> imageResponsesConverter(List<ProductImage> productImages) {

        List<ProductImageResponse> imageResult = productImages.stream().map(i -> new ProductImageResponse(
                i.getOriginalFileName(),
                i.getServerFileName()
        )).collect(Collectors.toList());

        return imageResult;
    }

}

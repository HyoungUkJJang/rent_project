package com.rent.rentshop.product.service;

import com.rent.rentshop.product.domain.Product;
import com.rent.rentshop.product.dto.ProductBest10Response;
import com.rent.rentshop.product.dto.ProductSimpleResponse;
import com.rent.rentshop.product.dto.ProductUpdate;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;

/**
 * 상품 저장, 조회, 수정, 삭제기능을 제공하는 서비스
 */
public interface ProductService {

    /**
     * 상품 전체를 조회해 상품목록을 슬라이스 형태로 리턴합니다.
     * @return 상품 리스트
     */
    Slice<ProductSimpleResponse> getProducts(Pageable pageable);

    /**
     * 주변 지역의 상품 전체를 조회해 상품목록을 슬라이스 형태로 리턴합니다.
     * @return 상품 리스트
     */
    Slice<ProductSimpleResponse> getAreaProducts(Pageable pageable, String city);

    /**
     * 주변 지역의 상품들 중 인기있는 10개의 상품을 리스트 형태로 리턴합니다.
     * @param city 주변지역
     * @return 상품 리스
     */
    List<ProductBest10Response> getBest10Products(String city);

    /**
     * 사용자가 등록한 상품만 조회해 상품목록을 리턴합니다.
     * @param userEmail 사용자 이메일
     * @return 조회된 상품목록
     */
    List<Product> getMyProducts(String userEmail);

    /**
     * 상품을 상세조회하여 리턴합니다.
     * @param id 조회할 상품의 아이디
     * @return 조회된 상품
     */
    Product getProduct(Long id);

    /**
     * 상품을 저장소에 저장하고 저장한 상품을 리턴합니다.
     * @param form 상품 정보
     * @param userEmail 상품을 등록하는 사용자 아이디
     * @return 저장된 상품
     */
    Product register(Product form, String userEmail);

    /**
     * 상품을 수정하여 저장소에 반영합니다.
     * @param id 수정할 상품의 아이디
     * @param form 수정할 상품의 정보
     */
    void update(Long id, ProductUpdate form);

    /**
     * 상품을 삭제합니다.
     * @param id 삭제할 상품의 아이디
     */
    void delete(Long id);

}

package com.rent.rentshop.product.repository;

import com.rent.rentshop.product.domain.Product;

import java.util.List;

public interface ProductRepositoryCustom {

    /**
     * 내가 등록한 상품을 조회하여 리턴합니다.
     * @param userId 사용자 이메일
     * @return 조회된 상품리스트
     */
    List<Product> getMyProducts(Long userId);

}

package com.rent.rentshop.product.repository;

import com.rent.rentshop.product.domain.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;

public interface ProductRepositoryCustom {

    /**
     * 내가 등록한 상품을 조회하여 리턴합니다.
     * @param userId 사용자 이메일
     * @return 조회된 상품리스트
     */
    List<Product> getMyProducts(Long userId);

    /**
     * 우리동네 주변 상품의 베스트 10을 보여줍니다.
     * @param city
     * @return
     */
    List<Product> getBest10Products(String city);

}

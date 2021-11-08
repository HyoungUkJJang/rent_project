package com.rent.rentshop.product.service;

import com.rent.rentshop.product.domain.Product;
import com.rent.rentshop.product.dto.ProductUpdate;
import java.util.List;

/**
 * 상품 저장, 조회, 수정, 삭제기능을 제공하는 서비스
 */
public interface ProductService {

    /**
     * 상품 전체를 조회해 상품목록을 리턴합니다.
     * @return 상품 리스트
     */
    List<Product> getProducts();

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

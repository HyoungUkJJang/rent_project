package com.rent.rentshop.product.repository;

import com.rent.rentshop.product.domain.Product;
import com.rent.rentshop.product.dto.ProductRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 저장소에서 상품을 등록, 조회, 수정, 삭제 기능을 제공하는 레포지토리 클래스.
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, ProductRepositoryCustom {

    /**
     * 상품을 등록 후 등록된 상품을 반환합니다.
     * @param form 등록할 상품의 정보
     * @return 등록된 상품
     */
    Product save(ProductRequest form);

    Slice<Product> findByCity(Pageable pageable, String city);

}

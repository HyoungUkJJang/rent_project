package com.rent.rentshop.product.repository;

import com.rent.rentshop.product.domain.Product;
import com.rent.rentshop.product.dto.ProductRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Override
    List<Product> findAll();

    Product save(ProductRequest form);

}

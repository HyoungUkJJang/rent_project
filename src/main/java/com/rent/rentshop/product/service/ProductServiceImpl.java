package com.rent.rentshop.product.service;

import com.rent.rentshop.error.ProductNotFoundException;
import com.rent.rentshop.error.UserNotFoundException;
import com.rent.rentshop.member.domain.User;
import com.rent.rentshop.member.repository.UserRepository;
import com.rent.rentshop.member.service.UserService;
import com.rent.rentshop.product.domain.Product;
import com.rent.rentshop.product.dto.ProductUpdate;
import com.rent.rentshop.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    @Override
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @Override
    @Transactional
    public Product register(Product form, String userEmail) {

        User findUser = userRepository.findByUserEmail(userEmail).orElseThrow(() -> new UserNotFoundException());

        form.setUser(findUser);

        Product registerProduct = productRepository.save(form);

        return registerProduct;

    }

    @Override
    public Product getProduct(Long id) {

        Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException());
        return product;

    }

    @Override
    @Transactional
    public void update(Long id, ProductUpdate form) {

        Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException());

        product.updateProduct(
                form.getProductName(),
                form.getProductDescription(),
                form.getProductPrice(),
                form.getDeposit(),
                form.getProductImg());

    }

    @Override
    @Transactional
    public void delete(Long id) {
        Product findProduct = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException());
        productRepository.delete(findProduct);
    }

}

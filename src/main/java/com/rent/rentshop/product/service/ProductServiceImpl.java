package com.rent.rentshop.product.service;

import com.rent.rentshop.error.ProductNotFoundException;
import com.rent.rentshop.error.UserNotFoundException;
import com.rent.rentshop.member.domain.User;
import com.rent.rentshop.member.repository.UserRepository;
import com.rent.rentshop.product.domain.Product;
import com.rent.rentshop.product.dto.ProductUpdate;
import com.rent.rentshop.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
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
    public Slice<Product> getProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public List<Product> getMyProducts(String userEmail) {
        User findUser = getFindUser(userEmail);
        List<Product> result = productRepository.getMyProducts(findUser.getId());
        return result;
    }

    @Override
    @Transactional
    public Product register(Product form, String userEmail) {

        User findUser = getFindUser(userEmail);

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
                form.getName(),
                form.getDescription(),
                form.getPrice(),
                form.getDeposit());

    }

    @Override
    @Transactional
    public void delete(Long id) {
        Product findProduct = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException());
        productRepository.delete(findProduct);
    }

    /**
     * 사용자를 찾아 리턴합니다.
     * @param userEmail 찾을 사용자 이메일
     * @return
     */
    private User getFindUser(String userEmail) {
        return userRepository.findByEmail(userEmail).orElseThrow(() -> new UserNotFoundException());
    }

}

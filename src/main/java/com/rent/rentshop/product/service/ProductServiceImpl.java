package com.rent.rentshop.product.service;

import com.rent.rentshop.error.ProductNotFoundException;
import com.rent.rentshop.error.UserNotFoundException;
import com.rent.rentshop.member.domain.User;
import com.rent.rentshop.member.repository.UserRepository;
import com.rent.rentshop.product.domain.Product;
import com.rent.rentshop.product.dto.ProductBest10Response;
import com.rent.rentshop.product.dto.ProductSimpleResponse;
import com.rent.rentshop.product.dto.ProductUpdate;
import com.rent.rentshop.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

    private String serverAddress = "https://rentproject.xyz/static/img/products/";
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    @Override
    public Slice<ProductSimpleResponse> getProducts(Pageable pageable) {

        return productRepository.findAll(pageable)
                .map(p -> new ProductSimpleResponse(
                        p.getId(),
                        p.getName(),
                        p.getPrice(),
                        serverAddress + p.getProductImages().get(0).getServerFileName(),
                        p.getCategory().getValue(),
                        p.getCity()
                ));

    }

    @Override
    public Slice<ProductSimpleResponse> getAreaProducts(Pageable pageable,String city) {

        return productRepository.findByCity(pageable, city)
                .map(p -> new ProductSimpleResponse(
                        p.getId(),
                        p.getName(),
                        p.getPrice(),
                        serverAddress + p.getProductImages().get(0).getServerFileName(),
                        p.getCategory().getValue(),
                        p.getCity()
                ));

    }

    @Override
    public List<ProductBest10Response> getBest10Products(String city) {

        return productRepository.getBest10Products(city)
                .stream().map(p -> new ProductBest10Response(
                        p.getId(),
                        serverAddress + p.getProductImages().get(0).getServerFileName(),
                        p.getName()
                )).collect(Collectors.toList());

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
     * ???????????? ?????? ???????????????.
     * @param userEmail ?????? ????????? ?????????
     * @return
     */
    private User getFindUser(String userEmail) {
        return userRepository.findByEmail(userEmail).orElseThrow(() -> new UserNotFoundException());
    }

}

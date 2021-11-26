package com.rent.rentshop.product.service;

import com.rent.rentshop.product.domain.Product;
import com.rent.rentshop.product.domain.ProductImage;
import com.rent.rentshop.product.file.ProductFileStore;
import com.rent.rentshop.product.repository.ProductImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProductImageServiceImpl implements ProductImageService {

    private final ProductImageRepository productImageRepository;
    private final ProductFileStore productFileStore;

    @Override
    @Transactional
    public void save(List<MultipartFile> multipartFiles, Product product) throws IOException {
        for (MultipartFile multipartFile : multipartFiles) {
            if (!multipartFile.isEmpty()) {
                ProductImage productImage = productFileStore.storeFile(multipartFile);
                productImage.setProduct(product);
                productImageRepository.save(productImage);
            }
        }

    }

}

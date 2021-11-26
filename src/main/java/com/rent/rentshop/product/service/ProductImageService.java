package com.rent.rentshop.product.service;

import com.rent.rentshop.product.domain.Product;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProductImageService {

    void save(List<MultipartFile> multipartFiles, Product product) throws IOException;


}

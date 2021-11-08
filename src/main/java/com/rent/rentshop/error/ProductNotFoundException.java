package com.rent.rentshop.error;

import java.util.NoSuchElementException;

public class ProductNotFoundException extends NoSuchElementException {
    public ProductNotFoundException() {
        super("상품을 찾을 수 없습니다.");
    }
}

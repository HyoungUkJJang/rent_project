package com.rent.rentshop.controller;

import com.rent.rentshop.error.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 상품과 관련된 HTTP 요청에 대하여 예외 처리를 담당하는 컨트롤러
 */
@ResponseBody
@ControllerAdvice
public class ProductErrorController {

    /**
     * 상품 상세조회 시 상품을 찾지 못할 경우, 404 상태코드를 응답합니다.
     * @return 상품을 찾을 수 없다는 응답 메시지
     */
    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ProductNotFoundException productNotFoundException() {
       return new ProductNotFoundException();
    }

}

package com.rent.rentshop.category.service;

import com.rent.rentshop.category.domain.Category;

import java.util.List;

/**
 * 카테고리 정보를 제공하는 클래스 입니다.
 */
public interface CategoryService {

    /**
     * 카테고리 리스트를 조회하여 리턴합니다.
     * @return 카테고리 리스트
     */
    List<Category> getCategoryList();

}

package com.rent.rentshop.category.domain;

import com.rent.rentshop.common.BaseTime;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 상품의 카테도리 도메인 클래스입니다.
 */
@Entity
@Getter
@NoArgsConstructor
public class Category extends BaseTime {

    @Id
    @Column(name = "category_id")
    private String id;
    private String value;

    public Category(String id, String value) {
        this.id = id;
        this.value = value;
    }

}

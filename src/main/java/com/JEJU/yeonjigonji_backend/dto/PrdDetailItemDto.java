package com.JEJU.yeonjigonji_backend.dto;

import com.JEJU.yeonjigonji_backend.entity.PrdItem;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PrdDetailItemDto {

    private Long id;

    private String brandNm; // 브랜드명

    private Integer price; //가격

    private PrdItem prdItem;

    private String repImg; // 대표 이미지

    private String imgTags; // 이미지 태그
}

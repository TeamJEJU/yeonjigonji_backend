package com.JEJU.yeonjigonji_backend.dto;

import com.JEJU.yeonjigonji_backend.entity.LikeItem;
import com.JEJU.yeonjigonji_backend.entity.PrdDetailItem;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class PrdDto {

    private String repImg; // 대표 이미지

    private String brand; // 브랜드명

    private String name; // 브랜드제품명색상명

    private Integer price; // 가격

    private String color; // 색상

    private String similarity; //색상 유사도

    private Boolean isLike; // 찜 유무
}

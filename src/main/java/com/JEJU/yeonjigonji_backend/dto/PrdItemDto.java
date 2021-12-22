package com.JEJU.yeonjigonji_backend.dto;

import com.JEJU.yeonjigonji_backend.entity.LikeItem;
import com.JEJU.yeonjigonji_backend.entity.PrdDetailItem;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PrdItemDto {

    private Long id;

    private String untNm; // 브랜드제품명색상명

    private String color; //색상 코드값

    private PrdDetailItem prdDetailItem;

    private List<LikeItem> likes = new ArrayList<>();
}

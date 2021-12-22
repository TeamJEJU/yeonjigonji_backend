package com.JEJU.yeonjigonji_backend.dto;

import com.JEJU.yeonjigonji_backend.entity.PrdItem;
import com.JEJU.yeonjigonji_backend.entity.PrdDetailItem;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;


@Getter
@Setter
public class PrdItemFormDto {
    private Long id;    //PrdDetailItem의 아이디 값을 가짐

    private String brandNm; // 브랜드명

    private String untNm; // 브랜드제품명색상명

    private String color; //색상 코드값

    private Integer price; //가격

    private String repImg; // 대표 이미지

    private String imgTags; // 이미지 태그


    public PrdItemFormDto(PrdItem prdItemAnother, PrdDetailItem prdDetailItemAnother){
        this.id = prdDetailItemAnother.getId();
        this.brandNm = prdDetailItemAnother.getBrandNm();
        this.untNm = prdItemAnother.getUntNm();
        this.color = prdItemAnother.getColor();
        this.price = prdDetailItemAnother.getPrice();
        this.repImg = prdDetailItemAnother.getRepImg();
        this.imgTags = prdDetailItemAnother.getImgTags();
    }

   // private static ModelMapper modelMapper = new ModelMapper();

}
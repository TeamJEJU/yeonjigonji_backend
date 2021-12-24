package com.JEJU.yeonjigonji_backend.dto;

import com.querydsl.core.annotations.QueryProjection;
import com.JEJU.yeonjigonji_backend.entity.PrdItem;
import com.JEJU.yeonjigonji_backend.entity.PrdDetailItem;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;


@Getter
@Setter
public class SearchItemDto {
    private Long id;    //PrdDetailItem의 아이디 값을 가짐

    private String brandNm; // 브랜드명

    private String untNm; // 브랜드제품명색상명

    private String color; //색상 코드값

    private Integer price; //가격

    private Integer similarity; //유사도

    private String repImg; // 대표 이미지


    @QueryProjection
    public SearchItemDto(PrdItem prdItemAnother, PrdDetailItem prdDetailItemAnother){
        this.id = prdDetailItemAnother.getId();
        this.brandNm = prdDetailItemAnother.getBrandNm();
        this.untNm = prdItemAnother.getUntNm();
        this.color = prdItemAnother.getColor();
        this.price = prdDetailItemAnother.getPrice();
        this.similarity = null;
        this.repImg = prdDetailItemAnother.getRepImg();
    }

    @QueryProjection
    public SearchItemDto(Long id, String untNm, String color, String brandNm, Integer price, String repImg){
        this.id = id;
        this.untNm = untNm;
        this.color = color;
        this.brandNm = brandNm;
        this.price = price;
        this.similarity = null;
        this.repImg = repImg;
    }



    // private static ModelMapper modelMapper = new ModelMapper();

}
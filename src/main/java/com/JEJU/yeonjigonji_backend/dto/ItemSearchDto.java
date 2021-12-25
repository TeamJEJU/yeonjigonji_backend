package com.JEJU.yeonjigonji_backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemSearchDto {

    private String searchBy;    //상품 조회할 때 어떤 유형으로 조회할지. itemNm: 상품명 조회, color: 색상 검색

    private String searchQuery = "";    //조회할 검색어를 저장하는 변수. itemNm: 상품명을 기준으로 검색, color: 색상명을 기준으로 검색

    public ItemSearchDto(String searchBy, String searchQuery) {
        this.searchBy = searchBy;
        this.searchQuery = searchQuery;
    }
}
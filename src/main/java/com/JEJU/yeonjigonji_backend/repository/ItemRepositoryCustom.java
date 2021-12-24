package com.JEJU.yeonjigonji_backend.repository;

import com.JEJU.yeonjigonji_backend.dto.ItemSearchDto;
import com.JEJU.yeonjigonji_backend.dto.SearchItemDto;
import com.JEJU.yeonjigonji_backend.entity.PrdDetailItem;
import com.JEJU.yeonjigonji_backend.entity.PrdItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ItemRepositoryCustom {
    Page<SearchItemDto> getSearchItemPage(ItemSearchDto itemSearchDto, Pageable pageable);
    //상품 조회 조건을 담고 있는 itemSearchDto 객체와 페이징 정보를 담고 있는 pageable 객체를 파라미터로 받아서 Page<SearchItemDto>객체 반환

    Page<SearchItemDto> getSearchColorSimilarity(ItemSearchDto itemSearchDto, Pageable pageable);
}

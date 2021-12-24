package com.JEJU.yeonjigonji_backend.service;


import com.JEJU.yeonjigonji_backend.dto.ItemSearchDto;
import com.JEJU.yeonjigonji_backend.dto.PrdItemDto;
import com.JEJU.yeonjigonji_backend.dto.PrdItemFormDto;
import com.JEJU.yeonjigonji_backend.dto.SearchItemDto;
import com.JEJU.yeonjigonji_backend.entity.PrdDetailItem;
import com.JEJU.yeonjigonji_backend.entity.PrdItem;
import com.JEJU.yeonjigonji_backend.repository.ItemRepository;
import com.JEJU.yeonjigonji_backend.repository.PrdItemRepository;
import com.JEJU.yeonjigonji_backend.repository.PrdDetailItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
@Transactional
@RequiredArgsConstructor
public class ItemService {

    private final PrdDetailItemRepository prdDetailRepository;
    private final PrdItemRepository prdItemRepository;
    private final ItemRepository itemRepository;

    @Transactional(readOnly = true)
    public PrdItemFormDto getItemDtl(Long prdId) {
            PrdItem prdItem = prdItemRepository.findById(prdId)
                    .orElseThrow(EntityNotFoundException::new);
            long prdDetailId = prdItem.getPrdDetailItem().getId();
            PrdDetailItem prdDetailItem = prdDetailRepository.findById(prdDetailId)
                    .orElseThrow(EntityNotFoundException::new);
            PrdItemFormDto prdItemFormDto = new PrdItemFormDto(prdItem, prdDetailItem);
            return prdItemFormDto;
    }

    //상품조회조건과 페이지 정보를 파라미터로 받아서 상품 데이터를 조회하는 getSearchItemPage() 메소드 추가
    @Transactional(readOnly = true)
    public Page<SearchItemDto> getSearchItemPage(ItemSearchDto itemSearchDto, Pageable pageable){
        return itemRepository.getSearchItemPage(itemSearchDto,pageable);
    }

}
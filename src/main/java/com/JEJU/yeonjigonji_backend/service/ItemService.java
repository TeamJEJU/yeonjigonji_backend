package com.JEJU.yeonjigonji_backend.service;


import com.JEJU.yeonjigonji_backend.dto.PrdItemDto;
import com.JEJU.yeonjigonji_backend.dto.PrdItemFormDto;
import com.JEJU.yeonjigonji_backend.entity.PrdDetailItem;
import com.JEJU.yeonjigonji_backend.entity.PrdItem;
import com.JEJU.yeonjigonji_backend.repository.PrdItemRepository;
import com.JEJU.yeonjigonji_backend.repository.PrdDetailItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
@Transactional
@RequiredArgsConstructor
public class ItemService {

    private final PrdDetailItemRepository prdDetailRepository;
    private final PrdItemRepository prdItemRepository;

    @Transactional(readOnly = true)
    public PrdItemFormDto getItemDtl(Long prdDetailId) {
        PrdItem prdItem = prdItemRepository.findByPrdDetailItemId(prdDetailId);
        PrdDetailItem prdDetailItem = prdDetailRepository.findById(prdDetailId)
                .orElseThrow(EntityNotFoundException::new);
        PrdItemFormDto prdItemFormDto = new PrdItemFormDto(prdItem,prdDetailItem);
        return  prdItemFormDto;
    }


}
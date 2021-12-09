package com.JEJU.yeonjigonji_backend.service;

import com.JEJU.yeonjigonji_backend.entity.PrdItem;
import com.JEJU.yeonjigonji_backend.repository.PrdItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class PrdItemService {
    private final PrdItemRepository prdRepository;

    public PrdItem findPrdItem(Long id){
        PrdItem item = prdRepository.findById(id).get();
        return item;
    }
}

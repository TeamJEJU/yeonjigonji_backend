package com.JEJU.yeonjigonji_backend.service;

import com.JEJU.yeonjigonji_backend.entity.PrdDetailItem;
import com.JEJU.yeonjigonji_backend.entity.PrdItem;
import com.JEJU.yeonjigonji_backend.repository.PrdDetailItemRepository;
import com.JEJU.yeonjigonji_backend.repository.PrdItemRepository;
import com.JEJU.yeonjigonji_backend.util.ExcelHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
@RequiredArgsConstructor
public class ExcelService {

    private final PrdItemRepository prdItemRepository;
    private final PrdDetailItemRepository prdDetailItemRepository;

    public void savePrdItem(){
        List<PrdItem> prdList = ExcelHelper.readItemExcel();
        prdItemRepository.saveAll(prdList);
    }

    public void savePrdDetailItem(){
        List<PrdDetailItem> prdDetailList = ExcelHelper.readDetailExcel();
        Long i = 0L;
        try {
            for (PrdDetailItem detail : prdDetailList) {
                if(detail.getPrdItem() != null) {
                    PrdItem prdItem = prdItemRepository.findById(detail.getId()).get();
                    detail.setPrdItem(prdItem);
                    detail.setId(i);
                    i++;
                }
            }
        }catch(NoSuchElementException e){
            e.printStackTrace();
        }
        prdDetailItemRepository.saveAll(prdDetailList);
    }

    public List<PrdItem> getAllPrd(){
        return prdItemRepository.findAll();
    }

    public List<PrdDetailItem> getAllDetail() {
        return prdDetailItemRepository.findAll();
    }

}


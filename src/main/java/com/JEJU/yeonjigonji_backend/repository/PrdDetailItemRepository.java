package com.JEJU.yeonjigonji_backend.repository;

import com.JEJU.yeonjigonji_backend.entity.PrdDetailItem;
import com.JEJU.yeonjigonji_backend.entity.PrdItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PrdDetailItemRepository extends PagingAndSortingRepository<PrdDetailItem, Long>, JpaSpecificationExecutor<PrdDetailItem> {
    //PrdDetailItem findById(Long id);
    PrdDetailItem findByPrdItem(PrdItem prdItem);
}

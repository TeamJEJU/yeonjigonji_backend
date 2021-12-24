package com.JEJU.yeonjigonji_backend.repository;

import com.JEJU.yeonjigonji_backend.entity.PrdDetailItem;
import com.JEJU.yeonjigonji_backend.entity.PrdItem;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PrdItemRepository extends PagingAndSortingRepository<PrdItem, Long>, JpaSpecificationExecutor<PrdItem> {
    PrdItem findByPrdDetailItemId(Long prdDetailItemId);

    PrdItem findPrdItemByPrdDetailItem(PrdDetailItem detailItem);
}

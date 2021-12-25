package com.JEJU.yeonjigonji_backend.repository;

import com.JEJU.yeonjigonji_backend.entity.PrdItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface ItemRepository extends JpaRepository<PrdItem, Long>,
        QuerydslPredicateExecutor<PrdItem>, ItemRepositoryCustom {
}

package com.JEJU.yeonjigonji_backend.repository;


import com.JEJU.yeonjigonji_backend.dto.ItemSearchDto;
import com.JEJU.yeonjigonji_backend.dto.QSearchItemDto;
import com.JEJU.yeonjigonji_backend.dto.SearchItemDto;
import com.JEJU.yeonjigonji_backend.entity.PrdDetailItem;
import com.JEJU.yeonjigonji_backend.entity.PrdItem;
import com.JEJU.yeonjigonji_backend.entity.QPrdDetailItem;
import com.JEJU.yeonjigonji_backend.entity.QPrdItem;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.thymeleaf.util.StringUtils;

import javax.persistence.EntityManager;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

//ItemRepositoryCustom 인터페이스를 구현하는 ItemRepositoryCustomImpl 클래스
public class ItemRepositoryCustomImpl implements ItemRepositoryCustom{

    //동적으로 쿼리를 생성하기 위한 JPAQueryFactory 클래스
    private JPAQueryFactory queryFactory;

    public ItemRepositoryCustomImpl(EntityManager em){
        this.queryFactory = new JPAQueryFactory(em);
    }

    //searchBy 값에 따라서 상품명에 검색어를 포함하고 있는 상품을 조회하도록 조건값을 반환
    private BooleanExpression searchByLike(String searchBy, String searchQuery){
        if(StringUtils.equals("itemNm",searchBy)) {
            return StringUtils.isEmpty(searchQuery) ?
                    null : QPrdItem.prdItem.untNm.like("%" + searchQuery + "%");
        }
        return null;
    }

    //텍스트 검색
    @Override
    public Page<SearchItemDto> getSearchItemPage(ItemSearchDto itemSearchDto, Pageable pageable){
        QPrdItem prdItem = QPrdItem.prdItem;
        QPrdDetailItem prdDetailItem = QPrdDetailItem.prdDetailItem;

        QueryResults<SearchItemDto> results = queryFactory
                .select(
                        new QSearchItemDto(
                                prdItem.id,
                                prdItem.untNm,
                                prdItem.color,
                                prdDetailItem.brandNm,
                                prdDetailItem.price,
                                prdDetailItem.repImg
                        )
                )
                .from(prdDetailItem)
                .join(prdDetailItem.prdItem, prdItem)
                .where(searchByLike(itemSearchDto.getSearchBy(), itemSearchDto.getSearchQuery()))
                .orderBy(QPrdItem.prdItem.id.asc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        List<SearchItemDto> content = results.getResults();
        long total = results.getTotal();
        return new PageImpl<>(content,pageable,total); //조회한 데이터를 Page클래스의 구현체인 PageImpl 객체로 변환.

    }


    @Override
    public Page<SearchItemDto> getSearchColorSimilarity(ItemSearchDto itemSearchDto, Pageable pageable){
        QPrdItem prdItem = QPrdItem.prdItem;
        QPrdDetailItem prdDetailItem = QPrdDetailItem.prdDetailItem;

        QueryResults<SearchItemDto> results = queryFactory
                .select(
                        new QSearchItemDto(
                                prdItem.id,
                                prdItem.untNm,
                                prdItem.color,
                                prdDetailItem.brandNm,
                                prdDetailItem.price,
                                prdDetailItem.repImg
                        )
                )
                .from(prdDetailItem)
                .join(prdDetailItem.prdItem, prdItem)
                .orderBy(QPrdItem.prdItem.id.asc())
                .fetchResults();

        long count = results.getTotal();
        double RED = Integer.parseInt(itemSearchDto.getSearchQuery().substring(0,2),16);
        double GRN = Integer.parseInt(itemSearchDto.getSearchQuery().substring(2,4),16);
        double BLU = Integer.parseInt(itemSearchDto.getSearchQuery().substring(4,6),16);

        for(int i=0; i<count; i++ ){
            double similarity = 0;
            String color = results.getResults().get(i).getColor();
            if(results.getResults().get(i).getColor()!=null) {
                double RR = Math.abs(RED-Integer.parseInt(color.substring(0,2),16));
                double GG = Math.abs(GRN-Integer.parseInt(color.substring(2,4),16));
                double BB = Math.abs(BLU-Integer.parseInt(color.substring(4,6),16));
                similarity = (1-((RR+GG+BB)/(255*3)))*100;
            }
            results.getResults().get(i).setSimilarity((int)similarity);
        }

        List<SearchItemDto> content = results.getResults().stream()
                .sorted(Comparator.comparing(SearchItemDto::getSimilarity, Comparator.reverseOrder())).collect(Collectors.toList());

        long total = results.getTotal();
        return new PageImpl<>(content,pageable,total);
    }

}

package com.JEJU.yeonjigonji_backend.service;

import com.JEJU.yeonjigonji_backend.dto.PrdDto;
import com.JEJU.yeonjigonji_backend.entity.PrdDetailItem;
import com.JEJU.yeonjigonji_backend.entity.PrdItem;
import com.JEJU.yeonjigonji_backend.repository.PrdDetailItemRepository;
import com.JEJU.yeonjigonji_backend.repository.PrdItemRepository;
import com.JEJU.yeonjigonji_backend.util.PagingHeaders;
import com.JEJU.yeonjigonji_backend.util.PagingResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class PrdService {
    private final PrdDetailItemRepository prdDetailRepository;
    private final PrdItemRepository prdItemRepository;

    List<PrdDto> prdDtoList;

    public PagingResponse get(Specification<PrdDetailItem> spec, HttpHeaders headers, Sort sort) {
        if (isRequestPaged(headers)) {
            return get(spec, buildPageRequest(headers, sort));
        } else {
            List<PrdDto> entities = prdDtoList;
            return new PagingResponse((long) entities.size(), 0L, 0L, 0L, 0L, entities);
        }
    }

    private boolean isRequestPaged(HttpHeaders headers) {
        return headers.containsKey(PagingHeaders.PAGE_NUMBER.getName()) && headers.containsKey(PagingHeaders.PAGE_SIZE.getName());
    }

    private Pageable buildPageRequest(HttpHeaders headers, Sort sort) {
        int page = Integer.parseInt(Objects.requireNonNull(headers.get(PagingHeaders.PAGE_NUMBER.getName())).get(0));
        int size = Integer.parseInt(Objects.requireNonNull(headers.get(PagingHeaders.PAGE_SIZE.getName())).get(0));
        return PageRequest.of(page, size, sort);
    }


    public PagingResponse get(Specification<PrdDetailItem> spec, Pageable pageable) {
        Page<PrdDetailItem> page = prdDetailRepository.findAll(spec, pageable);
        List<PrdDetailItem> content = page.getContent();
        for (PrdDetailItem item : content) {
            PrdDto prdDto = new PrdDto();
            PrdItem prdItem = prdItemRepository.findPrdItemByPrdDetailItem(item);

            prdDto.setRepImg(item.getRepImg());
            prdDto.setBrand(item.getBrandNm());
            prdDto.setName(prdItem.getUntNm());
            prdDto.setPrice(item.getPrice());
            prdDto.setSimilarity(prdItem.getColor());
            prdDto.setIsLike(false);

            prdDtoList.add(prdDto);
        }
        return new PagingResponse(page.getTotalElements(), (long) page.getNumber(), (long) page.getNumberOfElements(), pageable.getOffset(), (long) page.getTotalPages(), prdDtoList);
    }

}

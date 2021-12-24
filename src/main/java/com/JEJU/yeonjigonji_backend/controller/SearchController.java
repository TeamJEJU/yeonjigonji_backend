package com.JEJU.yeonjigonji_backend.controller;

import com.JEJU.yeonjigonji_backend.dto.PrdDto;
import com.JEJU.yeonjigonji_backend.entity.PrdDetailItem;
import com.JEJU.yeonjigonji_backend.service.PrdService;
import com.JEJU.yeonjigonji_backend.util.PagingHeaders;
import com.JEJU.yeonjigonji_backend.util.PagingResponse;
import lombok.RequiredArgsConstructor;
import net.kaczmarzyk.spring.data.jpa.domain.*;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/main")
@RestController
@RequiredArgsConstructor
public class SearchController {

    private final PrdService prdService;

    @GetMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<PrdDto>> get(
            @And({
                    @Spec(path = "repImage", params = "repImage", spec = Like.class),
                    @Spec(path = "name", params = "name", spec = Like.class),
                    @Spec(path = "price", params = "price", spec = LessThanOrEqual.class),
                    @Spec(path = "similarity", params = "similarity", spec = GreaterThanOrEqual.class),
                    @Spec(path = "isLike", params = "isLike", spec = Equal.class)
            }) Specification<PrdDetailItem> spec,
            Sort sort,
            @RequestHeader HttpHeaders headers) {
        final PagingResponse response = prdService.get(spec, headers, sort);
        return new ResponseEntity<>(response.getElements(), returnHttpHeaders(response), HttpStatus.OK);
    }

    public HttpHeaders returnHttpHeaders(PagingResponse response) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(PagingHeaders.COUNT.getName(), String.valueOf(response.getCount()));
        headers.set(PagingHeaders.PAGE_SIZE.getName(), String.valueOf(response.getPageSize()));
        headers.set(PagingHeaders.PAGE_OFFSET.getName(), String.valueOf(response.getPageOffset()));
        headers.set(PagingHeaders.PAGE_NUMBER.getName(), String.valueOf(response.getPageNumber()));
        headers.set(PagingHeaders.PAGE_TOTAL.getName(), String.valueOf(response.getPageTotal()));
        return headers;
    }
}

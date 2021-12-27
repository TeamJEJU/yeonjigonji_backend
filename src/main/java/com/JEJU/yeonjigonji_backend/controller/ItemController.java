package com.JEJU.yeonjigonji_backend.controller;

import com.JEJU.yeonjigonji_backend.dto.ItemSearchDto;
import com.JEJU.yeonjigonji_backend.dto.SearchItemDto;
import com.JEJU.yeonjigonji_backend.entity.PrdItem;
import com.JEJU.yeonjigonji_backend.service.ItemService;
import com.JEJU.yeonjigonji_backend.dto.PrdItemFormDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import java.util.Optional;


@RestController
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping(value = "/item/result/{itemId}")
    public PrdItemFormDto itemDtl(@PathVariable("itemId") Long itemId) {
        PrdItemFormDto prdItemFormDto = itemService.getItemDtl(itemId);
        return prdItemFormDto;
    }

    @PostMapping(value = {"/item/result", "item/result/{page}"})
    public Page<SearchItemDto> itemSearch(@RequestBody ItemSearchDto itemSearchDto,
                                                         @PathVariable("page") Optional<Integer> page){
        Page<SearchItemDto> items = Page.empty();
        if(StringUtils.equals("itemNm",itemSearchDto.getSearchBy())) {
            Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0, 10);
            items = itemService.getSearchItemPage(itemSearchDto, pageable);
        }else if(StringUtils.equals("color",itemSearchDto.getSearchBy())) {
            Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0, 10);
            items = itemService.getSearchColorSimilarity(itemSearchDto, pageable);
        }

        return items;
    }


}

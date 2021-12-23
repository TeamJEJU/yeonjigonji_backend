package com.JEJU.yeonjigonji_backend.controller;

import com.JEJU.yeonjigonji_backend.service.ItemService;
import com.JEJU.yeonjigonji_backend.dto.PrdItemFormDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping(value = "/item/{itemId}")
    public String itemDtl(Model model, @PathVariable("itemId") Long itemId) {
        PrdItemFormDto prdItemFormDto = itemService.getItemDtl(itemId);
        model.addAttribute("item", prdItemFormDto);
        return "item/itemDtl";
    }
}

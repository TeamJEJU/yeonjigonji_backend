package com.JEJU.yeonjigonji_backend.controller;

import com.JEJU.yeonjigonji_backend.service.ExcelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final ExcelService excelService;

    @GetMapping(value = "/")
    public String main() {
        excelService.savePrdItem();
        //excelService.savePrdDetailItem();
        return "main";
    }
}

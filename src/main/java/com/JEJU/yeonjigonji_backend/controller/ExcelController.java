package com.JEJU.yeonjigonji_backend.controller;

import com.JEJU.yeonjigonji_backend.service.ExcelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/api/excel")
@RestController
@RequiredArgsConstructor
public class ExcelController {
    private final ExcelService excelService;

    @GetMapping(value = "/prdItem")
    public ResponseEntity<String> insertPrdData(){
        excelService.savePrdItem();
        return ResponseEntity.status(HttpStatus.OK).body("Success to insert prd items");
    }

    @GetMapping(value = "/prdDetailItem")
    public ResponseEntity<String> insertPrdDetailData(){
        excelService.savePrdDetailItem();
        return ResponseEntity.status(HttpStatus.OK).body("Success to insert prd detail items");
    }
}

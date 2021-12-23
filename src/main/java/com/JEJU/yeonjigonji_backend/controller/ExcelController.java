//package com.JEJU.yeonjigonji_backend.controller;
//
//import com.JEJU.yeonjigonji_backend.service.ExcelService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/api/excel")
//public class ExcelController {
//    private final ExcelService excelService;
//
//    @GetMapping("/prdItem")
//    public ResponseEntity<String> insertPrdData(){
//        excelService.savePrdItem();
//        return ResponseEntity.status(HttpStatus.OK).body("Success to insert prd items");
//    }
//
//    @GetMapping("/prdDetailItem")
//    public ResponseEntity<String> insertPrdDetailData(){
//        excelService.savePrdDetailItem();
//        return ResponseEntity.status(HttpStatus.OK).body("Success to insert prd detail items");
//    }
//}

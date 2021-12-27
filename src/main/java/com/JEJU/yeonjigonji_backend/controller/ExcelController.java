package com.JEJU.yeonjigonji_backend.controller;

import com.JEJU.yeonjigonji_backend.constant.StatusEnum;
import com.JEJU.yeonjigonji_backend.entity.Message;
import com.JEJU.yeonjigonji_backend.service.ExcelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.Charset;


@RequestMapping("/api/excel")
@RestController
@RequiredArgsConstructor
public class ExcelController {
    private final ExcelService excelService;

    @GetMapping(value = "/prdItem")
    public ResponseEntity<Message> insertPrdData() {
        excelService.savePrdItem();
        Message message = new Message();
        message.setStatus(StatusEnum.OK);
        message.setMessage("Success to insert prd items");
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }

    @GetMapping(value = "/prdDetailItem")
    public ResponseEntity<Message> insertPrdDetailData() {
        excelService.savePrdDetailItem();
        Message message = new Message();
        message.setStatus(StatusEnum.OK);
        message.setMessage("Success to insert prd detail items");
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }
}

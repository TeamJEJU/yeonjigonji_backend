package com.JEJU.yeonjigonji_backend.controller;


import com.JEJU.yeonjigonji_backend.constant.StatusEnum;
import com.JEJU.yeonjigonji_backend.entity.Message;
import com.JEJU.yeonjigonji_backend.service.ExcelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MainController {

    @GetMapping(value = "/")
    public ResponseEntity<Message> main() {
        Message message = new Message();
        message.setStatus(StatusEnum.OK);
        message.setMessage("Main");
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }
}

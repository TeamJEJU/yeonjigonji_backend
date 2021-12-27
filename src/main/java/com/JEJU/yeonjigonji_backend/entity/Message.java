package com.JEJU.yeonjigonji_backend.entity;

import com.JEJU.yeonjigonji_backend.constant.StatusEnum;
import lombok.Data;

@Data
public class Message {
    private StatusEnum status;
    private String message;
    private Object data;

    public Message() {
        this.status = StatusEnum.BAD_REQUEST;
        this.data = null;
        this.message = null;
    }
}

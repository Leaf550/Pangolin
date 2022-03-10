package com.yuheng.pangolin.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseBody<T> {
    private int status;
    private T data;
    private String message;
}

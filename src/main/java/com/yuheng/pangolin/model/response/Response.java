package com.yuheng.pangolin.model.response;

import com.yuheng.pangolin.constant.ResponseMessage;
import com.yuheng.pangolin.constant.StatusCode;

public class Response {
    public static <T> ResponseBody<T> responseSuccessWithData(T data) {
        return responseData(StatusCode.OK, data, ResponseMessage.SUCCESS);
    }

    public static <T> ResponseBody<T> responseFailureWithData(StatusCode status, T data, ResponseMessage message) {
        return responseData(status, data, message);
    }

    public static <T> ResponseBody<T> responseSuccess() {
        return responseData(StatusCode.OK, null, ResponseMessage.SUCCESS);
    }

    public static <T> ResponseBody<T> responseFailure(StatusCode status, ResponseMessage message) {
        return responseData(status, null, message);
    }

    private static <T> ResponseBody<T> responseData(StatusCode status, T data, ResponseMessage message) {
        return new ResponseBody<>(status.getValue(), data, message.getValue());
    }
}

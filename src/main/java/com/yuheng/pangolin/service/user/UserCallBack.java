package com.yuheng.pangolin.service.user;

import com.yuheng.pangolin.constant.StatusCode;

@FunctionalInterface
public interface UserCallBack {
    void callback(StatusCode status);
}

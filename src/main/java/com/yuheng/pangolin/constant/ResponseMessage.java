package com.yuheng.pangolin.constant;

public enum ResponseMessage {
    SUCCESS("success"),
    FAILURE("failure");

    private final String value;

    private ResponseMessage(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

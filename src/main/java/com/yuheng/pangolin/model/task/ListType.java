package com.yuheng.pangolin.model.task;

public enum ListType {
    TODAY(0),
    IMPORTANT(1),
    ALL(2),
    COMPLETED(3);

    private final int value;

    private ListType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}

package com.yuheng.pangolin.model.task;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class Task {
    private String uid;
    private String taskId;
    private String title;
    private String comment;
    private Long date;
    private Long time;
    private boolean isImportant;
    private boolean isCompleted;
    private long createTime;
    private int priority;
    private String listId;
}

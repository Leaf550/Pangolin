package com.yuheng.pangolin.model.task;


import lombok.Data;

@Data
public class TaskList {
    private String listId;
    private String uid;
    private int listType;
    private String listName;
    private int listColor;
    private String imageName;
    private int taskCount;
    private int completedCount;
    private long createTime;
    private int sortedBy;
}

package com.yuheng.pangolin.model.task;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
public class HomeData {
//    private int todayCount;
//    private int importantCount;
//    private int allCount;
//    private int completedCount;
//    private TasksListPageData today;
//    private TasksListPageData important;
//    private TasksListPageData all;
//    private TasksListPageData completed;
    private List<TasksListPageData> otherList = new ArrayList<>();
}

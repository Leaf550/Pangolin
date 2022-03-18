package com.yuheng.pangolin.model.task;

import lombok.Data;

import java.util.ArrayList;

@Data
public class TasksListPageData {
    private ArrayList<TasksSection> sections = new ArrayList<>();
}

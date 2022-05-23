package com.yuheng.pangolin.model.task;

import lombok.Data;

import java.util.ArrayList;

@Data
public class TasksSection {
    private TaskList taskList;
    private ArrayList<Task> tasks;
}

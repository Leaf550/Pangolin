package com.yuheng.pangolin.repository.task;

import com.yuheng.pangolin.model.task.ListType;
import com.yuheng.pangolin.model.task.Task;
import com.yuheng.pangolin.model.task.TaskList;

import java.util.ArrayList;
import java.util.List;

public interface TaskRepository {
    List<TaskList> getAllTaskList(String userID);
    List<Task> getAllTask(String uid);
    ArrayList<Task> getAllTaskInList(String listID);
    TaskList getTaskList(String listID);
    Task getTask(String taskID);
    void addTask(Task task);
    void addTaskList(TaskList taskList);
    void updateTask(Task task);
    void deleteTask(String TaskID);
    int getListOrderBy(String listId);
}

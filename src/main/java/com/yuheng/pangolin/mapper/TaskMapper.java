package com.yuheng.pangolin.mapper;

import com.yuheng.pangolin.model.task.Task;
import com.yuheng.pangolin.model.task.TaskList;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface TaskMapper {
    List<TaskList> getAllTaskList(String uid);
    List<Task> getAllTask(String uid);
    ArrayList<Task> getAllTaskInList(String listID);
    TaskList getTaskList(String listID);
    Task getTask(String taskID);
    void addTaskList(TaskList list);
    void addTask(Task task);
    void updateTask(Task task);
    int getListOrderBy(String listId);
}

package com.yuheng.pangolin.repository.task;

import com.yuheng.pangolin.mapper.TaskMapper;
import com.yuheng.pangolin.model.task.ListType;
import com.yuheng.pangolin.model.task.Task;
import com.yuheng.pangolin.model.task.TaskList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TaskRepositoryImpl implements TaskRepository {

    private final TaskMapper taskMapper;

    @Autowired
    public TaskRepositoryImpl(TaskMapper taskMapper) {
        this.taskMapper = taskMapper;
    }

    @Override
    public List<TaskList> getAllTaskList(String userID) {
        return taskMapper.getAllTaskList(userID);
    }

    @Override
    public List<Task> getAllTask(String uid) {
        return taskMapper.getAllTask(uid);
    }

    @Override
    public ArrayList<Task> getAllTaskInList(String listID) {
        return taskMapper.getAllTaskInList(listID);
    }

    @Override
    public TaskList getTaskList(String listID) {
        return taskMapper.getTaskList(listID);
    }

    @Override
    public Task getTask(String taskID) {
        return taskMapper.getTask(taskID);
    }

    @Override
    public void addTask(Task task) {
        taskMapper.addTask(task);
    }

    @Override
    public void addTaskList(TaskList taskList) {
        taskMapper.addTaskList(taskList);
    }

    @Override
    public void updateTask(Task task) {

    }

    @Override
    public int getListOrderBy(String listId) {
        return taskMapper.getListOrderBy(listId);
    }
}

package com.yuheng.pangolin.service.task;

import com.yuheng.pangolin.model.task.Task;
import com.yuheng.pangolin.model.task.TaskList;
import com.yuheng.pangolin.repository.task.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    @Autowired
    TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public List<Task> getAllTask(String uid) {
        return taskRepository.getAllTask(uid);
    }

    @Override
    public List<TaskList> getAllTaskList(String uid) {
        return taskRepository.getAllTaskList(uid);
    }

    @Override
    public ArrayList<Task> getAllTaskInList(String uid, String listID) {
        if (!isTaskListBelongsToUser(uid ,listID)) {
            return new ArrayList<>();
        }
        ArrayList<Task> tasksList = taskRepository.getAllTaskInList(listID);
        tasksList.sort((o1, o2) -> { return (int)(o1.getCreateTime() - o2.getCreateTime());});

        return tasksList;
    }

    @Override
    public TaskList getTaskList(String uid, String listId) {
        if (isTaskListBelongsToUser(uid, listId)) {
            return taskRepository.getTaskList(listId);
        }
        return null;
    }

    @Override
    public Task getTask(String uid, String taskId) {
        if (isTaskBelongsToUser(uid, taskId)) {
            return taskRepository.getTask(taskId);
        }
        return null;
    }

    @Override
    public boolean isTaskListBelongsToUser(String uid, String listID) {
        TaskList list = taskRepository.getTaskList(listID);
        return list.getUid().equals(uid);
    }

    @Override
    public boolean isTaskBelongsToUser(String uid, String taskID) {
        Task task = taskRepository.getTask(taskID);
        return task.getUid().equals(uid);
    }

    @Override
    public boolean isTaskListNameExisted(String uid, String listName) {
        return taskRepository.getAllTaskList(uid)
                .stream()
                .filter((TaskList list) -> list.getListName().equals(listName))
                .toArray()
                .length != 0;
    }

    @Override
    public void addTaskList(String uid, TaskList list) {
        if (isTaskListNameExisted(uid, list.getListName())) {
            return;
        }
        taskRepository.addTaskList(list);
    }

    @Override
    public void addTask(Task task) {
        taskRepository.addTask(task);
    }

    @Override
    public void removeTask(String uid, String taskID) {

    }

    @Override
    public void setTaskCompleted(String uid, String taskID, boolean completed) {
        Task task = getTask(uid, taskID);
        if (task != null) {
            task.setCompleted(completed);
            taskRepository.updateTask(task);
        }
    }

    @Override
    public void editTask(String uid, Task newTask) {
        if (!isTaskBelongsToUser(uid, newTask.getTaskId())) {
            return;
        }
        taskRepository.updateTask(newTask);
    }

}

package com.yuheng.pangolin.service.task;

import com.yuheng.pangolin.model.task.Task;
import com.yuheng.pangolin.model.task.TaskList;

import java.util.ArrayList;
import java.util.List;

public interface TaskService {
    /**
     * 获取所有今日任务
     * @param uid 用户uid
     * @return 所有今日任务
     */
    List<Task> getAllTaskInToday(String uid);

    /**
     * 获取所有重要任务
     * @param uid 用户uid
     * @return 所有重要任务
     */
    List<Task> getAllTaskIsImportant(String uid);

    /**
     * 获取所有已完成任务
     * @param uid 用户uid
     * @return 所有已完成任务
     */
    List<Task> getAllTaskIsCompleted(String uid);

    /**
     * 获取某用户所有的任务
     * @param uid 用户uid
     * @return 该用户所有任务
     */
    List<Task> getAllTask(String uid);

    /**
     * 获取某用户所有的任务分组
     * @param uid 用户uid
     * @return 所有任务列表
     */
    List<TaskList> getAllTaskList(String uid);

    /**
     * 获取某用户某分组所有的任务
     * @param uid 用户uid
     * @param listID 列表（分组）ID
     * @return listID对应列表的所有任务
     */
    ArrayList<Task> getAllTaskInList(String uid, String listID);

    TaskList getTaskList(String uid, String listId);

    /**
     * 判断某个列表是否属于某人
     * @param uid 用户uid
     * @param listID 列表（分组）ID
     * @return 是否属于
     */
    boolean isTaskListBelongsToUser(String uid, String listID);

    /**
     * 判断某个任务是否属于某人
     * @param uid 用户uid
     * @param taskID 任务ID
     * @return 是否属于
     */
    boolean isTaskBelongsToUser(String uid, String taskID);

    /**
     * 判断某列表名称是否已经存在
     * @param uid 用户uid
     * @param listName 列表名称
     * @return 是否存在
     */
    boolean isTaskListNameExisted(String uid, String listName);

    /**
     * 新建一个列表
     * @param uid 用户uid
     * @param list 要新增的列表
     */
    void addTaskList(String uid, TaskList list);

    /**
     * 新建一个任务
     * @param task 要增加的任务
     */
    void addTask(Task task);

    /**
     * 删除任务
     * @param uid 用户uid
     * @param taskID 要删除的任务ID
     */
    void removeTask(String uid, String taskID);

//    void editTask(String uid, String )
}


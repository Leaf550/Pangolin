package com.yuheng.pangolin.controller.home;

import com.yuheng.pangolin.constant.RequestPathConstant;
import com.yuheng.pangolin.constant.ResponseMessage;
import com.yuheng.pangolin.constant.StatusCode;
import com.yuheng.pangolin.encryption.Encryptor;
import com.yuheng.pangolin.model.response.Response;
import com.yuheng.pangolin.model.response.ResponseBody;
import com.yuheng.pangolin.model.task.*;
import com.yuheng.pangolin.service.task.TaskService;
import com.yuheng.pangolin.service.token.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Component
@RestController
public class HomeController {

    private final TaskService taskService;
    private final TokenService tokenService;

    @Autowired
    HomeController(TaskService taskService, TokenService tokenService) {
        this.taskService = taskService;
        this.tokenService = tokenService;
    }

    @GetMapping(RequestPathConstant.HOME)
    ResponseBody<?> getHomeData(
            @RequestHeader("Authorization") String token
    ) {
        String uid = tokenService.getUserId(token);
        if (uid == null || uid.isEmpty()) {
            return Response.responseFailure(StatusCode.DID_NOT_SIGNIN, ResponseMessage.FAILURE);
        }
        HomeData homeData = new HomeData();
        homeData.setTodayCount(taskService.getAllTaskInToday(uid).size());
        homeData.setImportantCount(taskService.getAllTaskIsImportant(uid).size());
        homeData.setAllCount(taskService.getAllTask(uid).size());
        homeData.setCompletedCount(taskService.getAllTaskIsCompleted(uid).size());
        homeData.setToday(getTodayTasksPageData(uid));
        homeData.setImportant(getImportantTasksPageData(uid));
        homeData.setAll(getAllTasksPageData(uid));
        homeData.setCompleted(getCompletedTasksPageData(uid));
        List<TaskList> taskLists = taskService.getAllTaskList(uid);
        for (TaskList taskList : taskLists) {
            List<Task> tasks = taskService.getAllTaskInList(uid, taskList.getListId());
            taskList.setTaskCount(tasks.size());
            TasksListPageData pageData = getTasksInList(uid, taskList.getListId());
            homeData.getOtherList().add(pageData);
        }

        return Response.responseSuccessWithData(homeData);
    }

    @PostMapping("/addTaskList")
    ResponseBody<?> addTaskList(
            @RequestHeader("Authorization") String token,
            @RequestParam("list_name") String listName,
            @RequestParam("list_color") int listColor,
            @RequestParam("image_name") String imageName
    ) {
        String uid = tokenService.getUserId(token);
        if (uid == null || uid.isEmpty()) {
            return Response.responseFailure(StatusCode.DID_NOT_SIGNIN, ResponseMessage.FAILURE);
        }
        TaskList list = new TaskList();
        list.setListId(Encryptor.generateUUID());
        list.setUid(uid);
        list.setListType(4);
        list.setListName(listName);
        list.setListColor(listColor);
        list.setImageName(imageName);
        list.setTaskCount(0);
        list.setCompletedCount(0);
        list.setCreateTime(System.currentTimeMillis());
        list.setSortedBy(0);
        taskService.addTaskList(uid, list);
        return Response.responseSuccess();
    }

    private TasksListPageData getTodayTasksPageData(String uid) {
        List<Task> tasks = taskService.getAllTaskInToday(uid);
        return composeListPageData(tasks, uid);
    }

    private TasksListPageData getImportantTasksPageData(String uid) {
        List<Task> tasks = taskService.getAllTaskIsImportant(uid);
        return composeListPageData(tasks, uid);
    }

    private TasksListPageData getAllTasksPageData(String uid) {
        List<Task> tasks = taskService.getAllTask(uid);
        return composeListPageData(tasks, uid);
    }

    private TasksListPageData getCompletedTasksPageData(String uid) {
        List<Task> tasks = taskService.getAllTaskIsCompleted(uid);
        return composeListPageData(tasks, uid);
    }

    private TasksListPageData getTasksInList(String uid, String listId) {
        ArrayList<Task> tasksList = taskService.getAllTaskInList(uid, listId);
        TasksListPageData listPageData = new TasksListPageData();
        TasksSection section = new TasksSection();
        listPageData.getSections().add(section);

        TaskList taskList = taskService.getTaskList(uid, listId);

        section.setTaskList(taskList);
        section.setTasks(tasksList);

        return listPageData;
    }

    private TasksListPageData composeListPageData(List<Task> tasks, String uid) {
        TasksListPageData pageData = new TasksListPageData();

        ArrayList<String> listIds = new ArrayList<>();

        for (Task task : tasks) {
            String listId = task.getListId();
            if (!listIds.contains(listId)) {
                listIds.add(listId);
            }
        }

        for (String listId : listIds) {
            TasksSection section = new TasksSection();
            TaskList taskList = taskService.getTaskList(uid, listId);
            section.setTaskList(taskList);
            ArrayList<Task> tasksInSection = new ArrayList<>();
            for (Task task : tasks) {
                if (task.getListId().equals(listId)) {
                    tasksInSection.add(task);
                }
            }
            if (tasksInSection.size() == 0) {
                break;
            }
            section.setTasks(tasksInSection);
            pageData.getSections().add(section);
        }

        pageData.getSections().sort((o1, o2) -> {
            return (int)(o1.getTaskList().getCreateTime() - o2.getTaskList().getCreateTime());
        });

        return pageData;
    }

}

package com.yuheng.pangolin.controller.task;

import com.yuheng.pangolin.constant.ResponseMessage;
import com.yuheng.pangolin.constant.StatusCode;
import com.yuheng.pangolin.encryption.Encryptor;
import com.yuheng.pangolin.model.response.Response;
import com.yuheng.pangolin.model.response.ResponseBody;
import com.yuheng.pangolin.model.task.Task;
import com.yuheng.pangolin.service.task.TaskService;
import com.yuheng.pangolin.service.token.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Component
@RestController
public class AddTaskController {

    private final TaskService taskService;
    private final TokenService tokenService;

    @Autowired
    AddTaskController(TaskService taskService, TokenService tokenService) {
        this.taskService = taskService;
        this.tokenService = tokenService;
    }

    @PostMapping("/addTask")
    ResponseBody<?> addTask(
            @RequestHeader("Authorization") String token,
            @RequestParam("title") String title,
            @RequestParam(value = "comment", required = false) Optional<String> comment,
            @RequestParam(value = "date", required = false) Optional<Long> date,
            @RequestParam(value = "time", required = false) Optional<Long> time,
            @RequestParam("isImportant") boolean isImportant,
            @RequestParam("listId") String listId
    ) {
        String uid = tokenService.getUserId(token);
        if (uid == null || uid.isEmpty()) {
            return Response.responseFailure(StatusCode.DID_NOT_SIGNIN, ResponseMessage.FAILURE);
        }

        if (!taskService.isTaskListBelongsToUser(uid, listId)) {
            return Response.responseFailure(StatusCode.WRONG_USER, ResponseMessage.FAILURE);
        }

        Task newTask = new Task();
        newTask.setTaskId(Encryptor.generateUUID());
        newTask.setUid(uid);
        newTask.setTitle(title);
        newTask.setComment(comment.orElse(null));
        newTask.setDate(date.orElse(null));
        newTask.setTime(time.orElse(null));
        newTask.setImportant(isImportant);
        newTask.setCompleted(false);
        newTask.setCreateTime(System.currentTimeMillis());
        newTask.setPriority(0);
        newTask.setListId(listId);

        taskService.addTask(newTask);

        return Response.responseSuccess();
    }

}

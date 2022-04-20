package com.yuheng.pangolin.controller.task;

import com.yuheng.pangolin.constant.RequestPathConstant;
import com.yuheng.pangolin.constant.ResponseMessage;
import com.yuheng.pangolin.constant.StatusCode;
import com.yuheng.pangolin.model.response.Response;
import com.yuheng.pangolin.model.response.ResponseBody;
import com.yuheng.pangolin.model.task.Task;
import com.yuheng.pangolin.service.task.TaskService;
import com.yuheng.pangolin.service.token.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class EditTaskController {

    private final TaskService taskService;
    private final TokenService tokenService;

    @Autowired
    EditTaskController(
            TaskService taskService,
            TokenService tokenService
    ) {
        this.taskService = taskService;
        this.tokenService = tokenService;
    }

    @PostMapping(RequestPathConstant.EDIT_TASK)
    ResponseBody<?> editTask(
            @RequestHeader("authorization") String token,
            @RequestParam("taskId") String taskId,
            @RequestParam("title") String title,
            @RequestParam(value = "comment", required = false) Optional<String> comment,
            @RequestParam(value = "date", required = false) Optional<Long> date,
            @RequestParam(value = "time", required = false) Optional<Long> time,
            @RequestParam("isImportant") boolean isImportant,
            @RequestParam("isCompleted") boolean isCompleted,
            @RequestParam("priority") int priority,
            @RequestParam("listId") String listId
            ) {
        String uid = tokenService.getUserId(token);
        if (uid == null || uid.isEmpty()) {
            return Response.responseFailure(StatusCode.DID_NOT_SIGNIN, ResponseMessage.FAILURE);
        }

        if (taskService.isTaskBelongsToUser(uid, taskId)) {
            Task task = new Task();
            task.setUid(uid);
            task.setTaskId(taskId);
            task.setTitle(title);
            task.setComment(comment.orElse(null));
            task.setDate(date.orElse(null));
            task.setTime(time.orElse(null));
            task.setImportant(isImportant);
            task.setCompleted(isCompleted);
            task.setPriority(priority);
            task.setListId(listId);
            task.setShared(false);

            taskService.editTask(uid, task);
        } else {
            return Response.responseFailure(StatusCode.WRONG_USER, ResponseMessage.FAILURE);
        }

        return Response.responseSuccess();
    }

}

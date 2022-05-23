package com.yuheng.pangolin.controller.task;

import com.yuheng.pangolin.constant.RequestPathConstant;
import com.yuheng.pangolin.constant.ResponseMessage;
import com.yuheng.pangolin.constant.StatusCode;
import com.yuheng.pangolin.model.response.Response;
import com.yuheng.pangolin.model.response.ResponseBody;
import com.yuheng.pangolin.service.task.TaskService;
import com.yuheng.pangolin.service.token.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@Component
@RestController
public class CompleteTaskController {

    private final TokenService tokenService;
    private final TaskService taskService;

    @Autowired
    CompleteTaskController(
            TokenService tokenService,
            TaskService taskService
    ) {
        this.tokenService = tokenService;
        this.taskService = taskService;
    }

    @GetMapping(RequestPathConstant.SET_TASK_COMPLETED)
    ResponseBody<?> setTaskCompleted(
            @RequestHeader("Authorization") String token,
            @RequestParam("taskId") String taskId,
            @RequestParam("completed") boolean completed
    ) {
        String uid = tokenService.getUserId(token);
        if (uid == null || uid.isEmpty()) {
            return Response.responseFailure(StatusCode.DID_NOT_SIGNIN, ResponseMessage.FAILURE);
        }

        taskService.setTaskCompleted(uid, taskId, completed);

        return Response.responseSuccess();
    }

}

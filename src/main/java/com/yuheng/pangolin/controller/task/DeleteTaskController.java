package com.yuheng.pangolin.controller.task;

import com.yuheng.pangolin.constant.RequestPathConstant;
import com.yuheng.pangolin.constant.ResponseMessage;
import com.yuheng.pangolin.constant.StatusCode;
import com.yuheng.pangolin.model.response.Response;
import com.yuheng.pangolin.model.response.ResponseBody;
import com.yuheng.pangolin.service.task.TaskService;
import com.yuheng.pangolin.service.token.TokenService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeleteTaskController {

    private final TokenService tokenService;
    private final TaskService taskService;

    DeleteTaskController(
            TokenService tokenService,
            TaskService taskService
    ) {
        this.tokenService = tokenService;
        this.taskService = taskService;
    }

    @GetMapping(RequestPathConstant.DELETE_TASK)
    ResponseBody<?> deleteTask(
            @RequestHeader("Authorization") String token,
            @RequestParam("taskID") String taskID
    ) {
        String uid = tokenService.getUserId(token);
        if (uid == null || uid.isEmpty()) {
            return Response.responseFailure(StatusCode.DID_NOT_SIGNIN, ResponseMessage.FAILURE);
        }

        taskService.deleteTask(uid, taskID);

        return Response.responseSuccess();
    }

}

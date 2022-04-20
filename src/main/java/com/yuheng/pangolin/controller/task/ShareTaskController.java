package com.yuheng.pangolin.controller.task;

import com.yuheng.pangolin.constant.RequestPathConstant;
import com.yuheng.pangolin.constant.ResponseMessage;
import com.yuheng.pangolin.constant.StatusCode;
import com.yuheng.pangolin.model.response.Response;
import com.yuheng.pangolin.model.response.ResponseBody;
import com.yuheng.pangolin.service.task.TaskService;
import com.yuheng.pangolin.service.token.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ShareTaskController {

    private final TokenService tokenService;
    private final TaskService taskService;

    @Autowired
    ShareTaskController(
            TokenService tokenService,
            TaskService taskService
    ) {
        this.tokenService = tokenService;
        this.taskService = taskService;
    }

    @GetMapping(RequestPathConstant.SHARE_TASK)
    ResponseBody<?> shareTask(
            @RequestHeader("Authorization") String token,
            @RequestParam("taskID") String taskID
    ) {
        String uid = tokenService.getUserId(token);
        if (uid == null || uid.isEmpty()) {
            return Response.responseFailure(StatusCode.DID_NOT_SIGNIN, ResponseMessage.FAILURE);
        }

        taskService.shareTask(uid, taskID);
        return Response.responseSuccess();
    }

}

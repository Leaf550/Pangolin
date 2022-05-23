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
public class SetTaskImportantController {

    private final TokenService tokenService;
    private final TaskService taskService;

    SetTaskImportantController(
            TokenService tokenService,
            TaskService taskService
    ) {
        this.tokenService = tokenService;
        this.taskService = taskService;
    }

    @GetMapping(RequestPathConstant.SET_TASK_IMPORTANT)
    ResponseBody<?> setTaskImportant(
            @RequestHeader("authorization") String token,
            @RequestParam("taskID") String taskID,
            @RequestParam("important") boolean important
    ) {
        String uid = tokenService.getUserId(token);
        if (uid == null || uid.isEmpty()) {
            return Response.responseFailure(StatusCode.DID_NOT_SIGNIN, ResponseMessage.FAILURE);
        }

        taskService.setTaskImportant(uid, taskID, important);
        return Response.responseSuccess();
    }

}

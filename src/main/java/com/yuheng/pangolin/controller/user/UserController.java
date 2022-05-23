package com.yuheng.pangolin.controller.user;

import com.yuheng.pangolin.constant.RequestParameterConstant;
import com.yuheng.pangolin.constant.RequestPathConstant;
import com.yuheng.pangolin.constant.ResponseMessage;
import com.yuheng.pangolin.constant.StatusCode;
import com.yuheng.pangolin.model.user.Token;
import com.yuheng.pangolin.model.response.Response;
import com.yuheng.pangolin.model.response.ResponseBody;
import com.yuheng.pangolin.model.user.User;
import com.yuheng.pangolin.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicReference;

@Component
@RestController
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(RequestPathConstant.SIGN_UP)
    ResponseBody<?> signUp(
            @RequestParam(RequestParameterConstant.USERNAME) String username,
            @RequestParam(RequestParameterConstant.PASSWORD) String password
    ) {
        AtomicReference<StatusCode> statusCode = new AtomicReference<StatusCode>(null);
        Token token = userService.signUp(username, password, statusCode::set);
        if (statusCode.get() == StatusCode.OK) {
            return Response.responseSuccessWithData(token);
        }
        return Response.responseFailure(statusCode.get(), ResponseMessage.FAILURE);
    }

    @PostMapping(RequestPathConstant.SIGN_IN)
    ResponseBody<?> signIn(
            @RequestParam(RequestParameterConstant.USERNAME) String username,
            @RequestParam(RequestParameterConstant.PASSWORD) String password
    ) {
        AtomicReference<StatusCode> statusCode = new AtomicReference<StatusCode>(null);
        Token token = userService.signIn(username, password, statusCode::set);
        if (statusCode.get() == StatusCode.OK) {
            return Response.responseSuccessWithData(token);
        }
        return Response.responseFailure(statusCode.get(), ResponseMessage.FAILURE);
    }
}

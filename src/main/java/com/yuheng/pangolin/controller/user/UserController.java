package com.yuheng.pangolin.controller.user;

import com.yuheng.pangolin.constant.RequestParameterConstant;
import com.yuheng.pangolin.constant.RequestPathConstant;
import com.yuheng.pangolin.exception.user.UserException;
import com.yuheng.pangolin.model.Token;
import com.yuheng.pangolin.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Component
@RestController
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(RequestPathConstant.SIGN_UP)
    Token signUp(
            @RequestParam(RequestParameterConstant.USERNAME) String username,
            @RequestParam(RequestParameterConstant.PASSWORD) String password
    ) {
        try {
            return userService.signUp(username, password);
        } catch (UserException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @GetMapping(RequestPathConstant.SIGN_IN)
    Token signIn(
            @RequestParam(RequestParameterConstant.USERNAME) String username,
            @RequestParam(RequestParameterConstant.PASSWORD) String password
    ) {
        try {
            return userService.signIn(username, password);
        } catch (UserException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}

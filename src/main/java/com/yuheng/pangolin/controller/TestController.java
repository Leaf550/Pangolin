package com.yuheng.pangolin.controller;

import com.yuheng.pangolin.model.TestModel;
import com.yuheng.pangolin.service.test.TestService;
import com.yuheng.pangolin.service.token.TokenService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Component
@RestController
public class TestController {

    private final TestService testService;
    private final TokenService tokenService;

    @Autowired
    public TestController(
            TestService testService,
            TokenService tokenService
    ) {
        this.testService = testService;
        this.tokenService = tokenService;
    }

    @GetMapping("/testService")
    TestModel selectTestTableById(
            @RequestParam("id") int id
    ) {
        return testService.getTestDataById(id);
    }

    @GetMapping("/testParseToken")
    Claims testParseToken(
        @RequestParam("token") String token
    ) {
        return tokenService.parseToken(token);
    }
}

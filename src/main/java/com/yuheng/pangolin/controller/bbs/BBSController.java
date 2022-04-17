package com.yuheng.pangolin.controller.bbs;

import com.yuheng.pangolin.constant.RequestPathConstant;
import com.yuheng.pangolin.constant.ResponseMessage;
import com.yuheng.pangolin.constant.StatusCode;
import com.yuheng.pangolin.encryption.Encryptor;
import com.yuheng.pangolin.model.bbs.BBSComment;
import com.yuheng.pangolin.model.bbs.BBSHomeModel;
import com.yuheng.pangolin.model.bbs.BBSPost;
import com.yuheng.pangolin.model.bbs.BBSPostRes;
import com.yuheng.pangolin.model.response.Response;
import com.yuheng.pangolin.model.response.ResponseBody;
import com.yuheng.pangolin.service.bbs.BBSService;
import com.yuheng.pangolin.service.token.TokenService;
import com.yuheng.pangolin.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BBSController {

    private final TokenService tokenService;
    private final BBSService bbsService;

    @Autowired
    BBSController(
            TokenService tokenService,
            BBSService bbsService
    ) {
        this.bbsService = bbsService;
        this.tokenService = tokenService;
    }

    @GetMapping(RequestPathConstant.ALL_BBSPOST)
    ResponseBody<?> getAllBBSPost(
            @RequestHeader("Authorization") String token
    ) {
        String uid = tokenService.getUserId(token);
        if (uid == null || uid.isEmpty()) {
            return Response.responseFailure(StatusCode.DID_NOT_SIGNIN, ResponseMessage.FAILURE);
        }

        List<BBSPostRes> responsePosts = bbsService.getBBSHomeModel();
        if (responsePosts == null) {
            return Response.responseFailure(StatusCode.UNKNOWN_ERR, ResponseMessage.FAILURE);
        }

        BBSHomeModel response = new BBSHomeModel();
        response.setPosts(responsePosts);

        return Response.responseSuccessWithData(response);
    }

    @PostMapping(RequestPathConstant.CREATE_BBSPOST)
    ResponseBody<?> ceratePost(
            @RequestHeader("Authorization") String token,
            @RequestParam("content") String content,
            @RequestParam("taskId") String taskId
    ) {
        String uid = tokenService.getUserId(token);
        if (uid == null || uid.isEmpty()) {
            return Response.responseFailure(StatusCode.DID_NOT_SIGNIN, ResponseMessage.FAILURE);
        }

        BBSPost post = new BBSPost();
        post.setPostId(Encryptor.generateUUID());
        post.setAuthorId(uid);
        post.setCreateTime(System.currentTimeMillis());
        post.setContent(content);
        post.setTaskId(taskId);
        post.setPraiseCount(0);

        boolean succeeded = bbsService.createNewPost(post);

        return succeeded
                ? Response.responseSuccess()
                : Response.responseFailure(StatusCode.UNKNOWN_ERR, ResponseMessage.FAILURE);
    }

    @PostMapping(RequestPathConstant.CREATE_BBSCOMMENT)
    ResponseBody<?> createComment(
            @RequestHeader("Authorization") String token,
            @RequestParam("postId") String postId,
            @RequestParam("sourceUserId") String sourceUserId,
            @RequestParam(value = "targetUserId", required = false) Optional<String> targetUserId,
            @RequestParam("content") String content
    )  {
        String uid = tokenService.getUserId(token);
        if (uid == null || uid.isEmpty()) {
            return Response.responseFailure(StatusCode.DID_NOT_SIGNIN, ResponseMessage.FAILURE);
        }

        BBSComment comment = new BBSComment();
        comment.setCommentId(Encryptor.generateUUID());
        comment.setPostId(postId);
        comment.setSourceUserId(sourceUserId);
        comment.setTargetUserId(targetUserId.orElse(null));
        comment.setContent(content);
        comment.setCreateTime(System.currentTimeMillis());

        boolean succeeded = bbsService.createNewComment(comment);

        return succeeded
                ? Response.responseSuccess()
                : Response.responseFailure(StatusCode.UNKNOWN_ERR, ResponseMessage.FAILURE);
    }

}

package com.yuheng.pangolin.controller.upLoadImage;

import com.yuheng.pangolin.config.ImagePathConfig;
import com.yuheng.pangolin.constant.ResponseMessage;
import com.yuheng.pangolin.constant.StatusCode;
import com.yuheng.pangolin.model.response.Response;
import com.yuheng.pangolin.model.response.ResponseBody;
import com.yuheng.pangolin.service.token.TokenService;
import com.yuheng.pangolin.service.upload.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
public class UpLoadImageController {

    private final ImagePathConfig imagePathConfig;
    private final TokenService tokenService;
    private final UploadService uploadService;

    @Autowired
    UpLoadImageController(
            ImagePathConfig imagePathConfig,
            TokenService tokenService,
            UploadService uploadService
    ) {
        this.imagePathConfig = imagePathConfig;
        this.tokenService = tokenService;
        this.uploadService = uploadService;
    }

    @PostMapping("/upLoadImage/bbs")
    @org.springframework.web.bind.annotation.ResponseBody
    ResponseBody<?> upLoadImageBBS(
            @RequestHeader("authorization") String token,
            @RequestParam("images") MultipartFile[] images
    ) {
        String uid = tokenService.getUserId(token);
        if (uid == null || uid.isEmpty()) {
            return Response.responseFailure(StatusCode.DID_NOT_SIGNIN, ResponseMessage.FAILURE);
        }

        List<String> urls = uploadService.uploadImage(
                images,
                uid,
                imagePathConfig.getBbsPath()
        );

        return Response.responseSuccessWithData(urls);
    }

    @PostMapping("/upLoadImage/profile")
    @org.springframework.web.bind.annotation.ResponseBody
    ResponseBody<?> upLoadImageProfile(
            @RequestHeader("authorization") String token,
            @RequestParam("images") MultipartFile[] images
    ) {
        String uid = tokenService.getUserId(token);
        if (uid == null || uid.isEmpty()) {
            return Response.responseFailure(StatusCode.DID_NOT_SIGNIN, ResponseMessage.FAILURE);
        }

        List<String> urls = uploadService.uploadImage(
                images,
                uid,
                imagePathConfig.getBbsPath()
        );

        return Response.responseSuccessWithData(urls);
    }

}

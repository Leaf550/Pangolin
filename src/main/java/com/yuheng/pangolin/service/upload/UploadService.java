package com.yuheng.pangolin.service.upload;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface UploadService {
    List<String> uploadImage(MultipartFile[] files, String uid, String postId, String subPath);
}

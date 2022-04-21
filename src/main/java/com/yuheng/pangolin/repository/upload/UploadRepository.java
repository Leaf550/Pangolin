package com.yuheng.pangolin.repository.upload;

import java.util.List;

public interface UploadRepository {
    void saveImagePath(String uid, String url, String postId);
    List<String> getImageUrlsByPostId(String postId);
}

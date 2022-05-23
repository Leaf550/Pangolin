package com.yuheng.pangolin.service.bbs;

import com.yuheng.pangolin.model.bbs.BBSComment;
import com.yuheng.pangolin.model.bbs.BBSPost;
import com.yuheng.pangolin.model.bbs.BBSPostRes;

import java.util.List;

public interface BBSService {
    List<BBSPostRes> getBBSHomeModel(String uid);
    boolean createNewPost(BBSPost post);
    boolean createNewComment(BBSComment comment);
    boolean isPostBelongsToUser(String postId, String uid);
    boolean isCommentBelongsToUser(String commentId, String uid);
    void praisePost(String uid, String postId);
}

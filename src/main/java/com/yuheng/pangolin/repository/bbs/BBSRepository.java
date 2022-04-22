package com.yuheng.pangolin.repository.bbs;

import com.yuheng.pangolin.model.bbs.BBSComment;
import com.yuheng.pangolin.model.bbs.BBSPost;

import java.util.List;

public interface BBSRepository {
    List<BBSPost> getAllPost();
    List<BBSComment> getAllCommentForPost(String postId);
    BBSPost getPostById(String postId);
    BBSComment getCommentById(String commentId);
    boolean addNewPost(BBSPost post);
    boolean addNewComment(BBSComment comment);
    void updatePost(BBSPost post);
    void praisePost(String uid, String postId);
    List<String> praisedUsersInPost(String postId);
    List<String> praisedPostsByUser(String uid);
}

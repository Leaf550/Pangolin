package com.yuheng.pangolin.mapper;

import com.yuheng.pangolin.model.bbs.BBSComment;
import com.yuheng.pangolin.model.bbs.BBSPost;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BBSMapper {
    List<BBSPost> selectAllPost();
    List<BBSComment> selectAllCommentForPostId(String postId);
    BBSPost selectPostById(String postId);
    BBSComment selectCommentWithId(String commentId);
    boolean addNewPost(BBSPost post);
    void updatePost(BBSPost post);
    boolean addNewComment(BBSComment comment);
}

package com.yuheng.pangolin.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PraiseMapper {
    void addPraise(String postId, String uid);
    List<String> praisedUsersInPost(String postId);
    List<String> praisedPostsByUser(String uid);
}

package com.yuheng.pangolin.repository.bbs;

import com.yuheng.pangolin.mapper.BBSMapper;
import com.yuheng.pangolin.model.bbs.BBSComment;
import com.yuheng.pangolin.model.bbs.BBSPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BBSRepositoryImpl implements BBSRepository {

    private final BBSMapper bbsMapper;

    @Autowired
    BBSRepositoryImpl(BBSMapper bbsMapper) {
        this.bbsMapper = bbsMapper;
    }

    @Override
    public List<BBSPost> getAllPost() {
        return bbsMapper.selectAllPost();
    }

    @Override
    public List<BBSComment> getAllCommentForPost(String postId) {
        return bbsMapper.selectAllCommentForPostId(postId);
    }

    @Override
    public BBSPost getPostById(String postId) {
        return bbsMapper.selectPostById(postId);
    }

    @Override
    public BBSComment getCommentById(String commentId) {
        return bbsMapper.selectCommentWithId(commentId);
    }

    @Override
    public boolean addNewPost(BBSPost post) {
        return bbsMapper.addNewPost(post);
    }

    @Override
    public boolean addNewComment(BBSComment comment) {
        return bbsMapper.addNewComment(comment);
    }
}

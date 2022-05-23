package com.yuheng.pangolin.repository.bbs;

import com.yuheng.pangolin.mapper.BBSMapper;
import com.yuheng.pangolin.mapper.PraiseMapper;
import com.yuheng.pangolin.model.bbs.BBSComment;
import com.yuheng.pangolin.model.bbs.BBSPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BBSRepositoryImpl implements BBSRepository {

    private final BBSMapper bbsMapper;
    private final PraiseMapper praiseMapper;

    @Autowired
    BBSRepositoryImpl(
            BBSMapper bbsMapper,
            PraiseMapper praiseMapper
    ) {
        this.bbsMapper = bbsMapper;
        this.praiseMapper = praiseMapper;
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

    @Override
    public void updatePost(BBSPost post) {
        bbsMapper.updatePost(post);
    }

    @Override
    public void praisePost(String uid, String postId) {
        praiseMapper.addPraise(postId, uid);
    }

    @Override
    public List<String> praisedUsersInPost(String postId) {
        return praiseMapper.praisedUsersInPost(postId);
    }

    @Override
    public List<String> praisedPostsByUser(String uid) {
        return praiseMapper.praisedPostsByUser(uid);
    }
}

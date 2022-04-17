package com.yuheng.pangolin.service.bbs;

import com.yuheng.pangolin.model.bbs.BBSComment;
import com.yuheng.pangolin.model.bbs.BBSCommentRes;
import com.yuheng.pangolin.model.bbs.BBSPost;
import com.yuheng.pangolin.model.bbs.BBSPostRes;
import com.yuheng.pangolin.model.user.User;
import com.yuheng.pangolin.model.user.UserRes;
import com.yuheng.pangolin.repository.bbs.BBSRepository;
import com.yuheng.pangolin.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BBSServiceImpl implements BBSService {

    private final UserService userService;
    private final BBSRepository bbsRepository;

    @Autowired
    BBSServiceImpl(
            UserService userService,
            BBSRepository bbsRepository
    ) {
        this.userService = userService;
        this.bbsRepository = bbsRepository;
    }

    @Override
    public List<BBSPostRes> getBBSHomeModel() {
        List<BBSPost> posts = bbsRepository.getAllPost();
        if (posts == null) {
            return null;
        }

        List<BBSPostRes> responsePosts = new ArrayList<>();
        for (BBSPost post: posts) {
            BBSPostRes postRes = new BBSPostRes();
            postRes.setPostId(post.getPostId());
            postRes.setCreateTime(post.getCreateTime());
            postRes.setContent(post.getContent());
            postRes.setTaskId(post.getTaskId());
            postRes.setPraiseCount(post.getPraiseCount());
            UserRes author = userService.getUserById(post.getAuthorId());
            if (author != null) {
                postRes.setAuthor(author);
            } else {
                continue;
            }

            List<BBSComment> comments = bbsRepository.getAllCommentForPost(post.getPostId());
            if (comments != null) {
                comments.sort((o1, o2) -> {
                    return (int) (o1.getCreateTime() - o2.getCreateTime());
                });
                List<BBSCommentRes> responseComments = new ArrayList<>();
                for (BBSComment comment : comments) {
                    BBSCommentRes commentRes = new BBSCommentRes();
                    commentRes.setCommentId(comment.getCommentId());
                    commentRes.setPostId(comment.getPostId());
                    commentRes.setCreateTime(comment.getCreateTime());
                    commentRes.setContent(comment.getContent());
                    UserRes source = userService.getUserById(comment.getSourceUserId());
                    UserRes target = userService.getUserById(comment.getTargetUserId());
                    if (source != null) {
                        commentRes.setSourceUser(source);
                    } else {
                        continue;
                    }
                    if (target != null) {
                        commentRes.setTargetUser(target);
                    }
                    responseComments.add(commentRes);
                }
                postRes.setCommentList(responseComments);
            } else {
                postRes.setCommentList(new ArrayList<>());
            }

            responsePosts.add(postRes);
        }

        responsePosts.sort((o1, o2) -> {
            return (int)(o1.getCreateTime() - o2.getCreateTime());
        });

        return responsePosts;
    }

    @Override
    public boolean createNewPost(BBSPost post) {
        return bbsRepository.addNewPost(post);
    }

    @Override
    public boolean createNewComment(BBSComment comment) {
        return bbsRepository.addNewComment(comment);
    }

    @Override
    public boolean isPostBelongsToUser(String postId, String uid) {
        BBSPost post = bbsRepository.getPostById(postId);
        return post.getAuthorId().equals(uid);
    }

    @Override
    public boolean isCommentBelongsToUser(String commentId, String uid) {
        BBSComment comment = bbsRepository.getCommentById(commentId);
        return comment.getSourceUserId().equals(uid);
    }
}

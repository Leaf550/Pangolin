package com.yuheng.pangolin.model.bbs;

import com.yuheng.pangolin.model.task.Task;
import com.yuheng.pangolin.model.user.UserRes;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BBSPostRes {
    private String postId;
    private UserRes author;
    private long createTime;
    private String content;
    private Task task;
    private List<String> imageUrls;
    private int praiseCount;
    private List<BBSCommentRes> commentList;
}

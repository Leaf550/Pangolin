package com.yuheng.pangolin.model.bbs;

import com.yuheng.pangolin.model.user.UserRes;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BBSCommentRes {
    private String commentId;
    private String postId;
    private UserRes sourceUser;
    private UserRes targetUser;
    private long createTime;
    private String content;
}

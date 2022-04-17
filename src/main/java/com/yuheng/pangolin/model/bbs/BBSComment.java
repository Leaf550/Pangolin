package com.yuheng.pangolin.model.bbs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BBSComment {
    private String commentId;
    private String postId;
    private String sourceUserId;
    private String targetUserId;
    private long createTime;
    private String content;
}

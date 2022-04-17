package com.yuheng.pangolin.model.bbs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BBSPost {
    private String postId;
    private String authorId;
    private long createTime;
    private String content;
    private String taskId;
    private int praiseCount;
}

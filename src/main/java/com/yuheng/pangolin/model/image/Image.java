package com.yuheng.pangolin.model.image;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Image {
    private String uid;
    private String url;
    private String postId;
}

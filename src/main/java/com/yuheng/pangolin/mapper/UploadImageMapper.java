package com.yuheng.pangolin.mapper;

import com.yuheng.pangolin.model.image.Image;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UploadImageMapper {
    void saveImage(String uid, String url, String postId);
    List<Image> getImageByPostId(String postId);
    void updateImagePostId(String url, String postId);
}

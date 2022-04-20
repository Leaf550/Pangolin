package com.yuheng.pangolin.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UploadImageMapper {
    void saveImage(String uid, String url);
}

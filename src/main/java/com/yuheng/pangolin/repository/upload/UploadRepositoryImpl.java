package com.yuheng.pangolin.repository.upload;

import com.yuheng.pangolin.mapper.UploadImageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UploadRepositoryImpl implements UploadRepository {

    private final UploadImageMapper uploadImageMapper;

    @Autowired
    UploadRepositoryImpl(
            UploadImageMapper uploadImageMapper
    ) {
        this.uploadImageMapper = uploadImageMapper;
    }

    @Override
    public void saveImagePath(String uid, String url) {
        uploadImageMapper.saveImage(uid, url);
    }

}

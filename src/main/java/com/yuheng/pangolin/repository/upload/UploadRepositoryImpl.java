package com.yuheng.pangolin.repository.upload;

import com.yuheng.pangolin.mapper.UploadImageMapper;
import com.yuheng.pangolin.model.image.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

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
    public void saveImagePath(String uid, String url, String postId) {
        uploadImageMapper.saveImage(uid, url, postId);
    }

    @Override
    public List<String> getImageUrlsByPostId(String postId) {
        return uploadImageMapper.getImageByPostId(postId)
                .stream()
                .map(Image::getUrl)
                .collect(Collectors.toList());
    }

    @Override
    public void updateImagePostId(String url, String postId) {
        uploadImageMapper.updateImagePostId(url, postId);
    }

}

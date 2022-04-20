package com.yuheng.pangolin.service.upload;

import com.yuheng.pangolin.config.ImagePathConfig;
import com.yuheng.pangolin.config.ServerURLConfig;
import com.yuheng.pangolin.encryption.Encryptor;
import com.yuheng.pangolin.repository.upload.UploadRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
@Data
public class UploadServiceImpl implements UploadService {

    private final ServerURLConfig urlConfig;
    private final ImagePathConfig imagePathConfig;
    private final UploadRepository uploadRepository;

    @Autowired
    UploadServiceImpl(
            ServerURLConfig urlConfig,
            ImagePathConfig imagePathConfig,
            UploadRepository uploadRepository
    ) {
        this.urlConfig = urlConfig;
        this.imagePathConfig = imagePathConfig;
        this.uploadRepository = uploadRepository;
    }

    @Override
    public List<String> uploadImage(MultipartFile[] files, String uid, String subPath) {
        String dir = imagePathConfig.getLocalPath() + subPath;
        File targetPath = new File(dir);

        List<String> urls = new ArrayList<>();
        try {
            for (MultipartFile file : files) {
                String fileName = Encryptor.generateUUID() + ".png";
                File targetFile = new File(targetPath, fileName);
                if (file.isEmpty()) {
                    continue;
                }
                String url = urlConfig.getProtocol() + "://"
                        + urlConfig.getHost() + ":"
                        + urlConfig.getPort()
                        + imagePathConfig.getBasePath()
                        + subPath
                        + fileName;
                uploadRepository.saveImagePath(uid, url);
                file.transferTo(targetFile);
                urls.add(url);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return urls;
    }
}

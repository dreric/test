package com.lxcm.common.service;

import com.lxcm.common.model.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @Classname FileService
 * @Description 文件上传服务
 * @Date 2020-01-19 17:54
 * @Created by lx
 */
@Service
public class FileService {

    @Autowired(required = false)
    private HttpSession session;

    @Value("${file.location}")
    private String fileLocation;

    @Value("${file.server}")
    private String fileServer;


    /**
     * 上传文件到服务器
     *
     * @param uploadFile
     * @return
     */
    public Results uplaod(MultipartFile uploadFile) throws IOException {

        String fileName = uploadFile.getOriginalFilename();
        String suffix = fileName.substring(fileName.lastIndexOf(".") - 1);
        String uuid = UUID.randomUUID().toString().toUpperCase().replace("-", "");
        fileName = uuid + suffix;

        String parent = session.getServletContext().getRealPath(fileLocation);
        File file = new File(parent, fileName);
        if (!file.exists()) {
            file.mkdirs();
        }
        uploadFile.transferTo(file);
        return Results.success(fileName,fileServer + "/" + fileName);
    }
}

package com.lxcm.common.controller;


import com.lxcm.common.model.Results;
import com.lxcm.common.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/common/file")
public class FileController {

    @Autowired
    private FileService fileService;

    /**
     * 单文件上传
     *
     * @param uploadFile
     * @return
     */
    @PostMapping("/upload")
    @ResponseBody
    public Results uplaod(@RequestParam("uploadFile") MultipartFile uploadFile) {
        try {
            return fileService.uplaod(uploadFile);
        } catch (IOException e) {
            e.printStackTrace();
            return Results.failure(801,e.getMessage());
        }
    }

    /**
     * 多文件上传
     *
     * @param uploadFile
     * @return
     */
    @PostMapping("/uploads")
    @ResponseBody
    public Results uplaods(@RequestParam("uploadFile") MultipartFile[] uploadFile) {
        List<Results> data = new ArrayList<Results>();
        for (MultipartFile f : uploadFile) {
            try {
                data.add(fileService.uplaod(f));
            } catch (IOException e) {
                e.printStackTrace();
                data.add(Results.failure(801,e.getMessage()));
            }
        }
        return Results.success(data.size(),data);
    }
}

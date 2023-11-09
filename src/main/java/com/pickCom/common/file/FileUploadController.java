package com.pickCom.common.file;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class FileUploadController {
    @Autowired
    private FileUploadService fileUploadService;

    @PostMapping("/upload")
    public String handleFileUpload(@RequestParam("file") MultipartFile file) {
        try {
            fileUploadService.uploadFile(file);
            return "redirect:/success"; // 업로드 성공 시 리다이렉트
        } catch (IOException e) {
            return "redirect:/error"; // 업로드 실패 시 리다이렉트
        }
    }
}
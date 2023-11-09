package com.pickCom.common.file;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

// 파일 다운로드 컨트롤러
@Controller
public class FileDownloadController {
    @Value("${uploadDir}")
    private String uploadDir;

    @GetMapping("/download/{fileName:.+}")
    public void downloadFile(@PathVariable String fileName, HttpServletResponse response) {
        File file = new File(uploadDir + fileName);
        if (file.exists()) {
            try (InputStream is = new FileInputStream(file)) {
                response.setHeader("Content-Disposition", "attachment; filename=" + file.getName());
                response.setContentType("application/octet-stream");
                IOUtils.copy(is, response.getOutputStream());
                response.flushBuffer();
            } catch (IOException e) {
                // Handle exception
            }
        } else {
            // File not found
        }
    }
}
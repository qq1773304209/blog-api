package com.jonm.service;

import com.jonm.model.vo.Result;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UploadService {
    public Result uploadImage(MultipartFile file);
    public String saveFile(MultipartFile multipartFile) throws IOException;
}

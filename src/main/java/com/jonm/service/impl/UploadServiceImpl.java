package com.jonm.service.impl;

import com.jonm.model.bean.FastDFSFile;
import com.jonm.model.vo.Result;
import com.jonm.service.UploadService;
import com.jonm.util.fastdfs.FastDFSClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Pattern;

/**
 * @program: blog-api
 * @description: 文件上传
 * @author: Jonm
 * @create: 2021-03-29 23:43
 **/
@Service
@Slf4j
public class UploadServiceImpl implements UploadService {
    //上传文件路径
    @Value("${file.base.director}")
    private String fileBaseDirector;
    //客户访问文件路径
    @Value("${fastdfs.nginx.url}")
    private String baseurl;

    @Override
    public Result uploadImage(MultipartFile file){
        if (file.isEmpty()) {
            return Result.error("文件为空");
        }
        String fileName = file.getOriginalFilename();  // 文件名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));  // 后缀名
        String reg = "\\.(png|jpe?g|gif|svg)(\\?.*)?$";
        boolean isMatch = Pattern.matches(reg, suffixName);
        if(!isMatch){
            return Result.error("格式错误");
        }
        fileName = UUID.randomUUID() + suffixName; // 新文件名
        // 上传到fastDFS服务器

        File dest = new File(fileBaseDirector + fileName);
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Map<String, Object> map = new HashMap<>();
        map.put("originalURL","");
        map.put("url","");
        return Result.ok("上传成功",map);
    }

    @Override
    public String saveFile(MultipartFile multipartFile) throws IOException {
        String[] fileAbsolutePath={};
        String fileName=multipartFile.getOriginalFilename();
        String ext = fileName.substring(fileName.lastIndexOf(".") + 1);
        byte[] file_buff = null;
        InputStream inputStream=multipartFile.getInputStream();
        if(inputStream != null){
            int len1 = inputStream.available();
            file_buff = new byte[len1];
            inputStream.read(file_buff);
        }
        inputStream.close();
        FastDFSFile file = new FastDFSFile(fileName, file_buff, ext);
        try {
            fileAbsolutePath = FastDFSClient.upload(file);  //upload to fastdfs
        } catch (Exception e) {
            log.error("upload file Exception!",e);
            return null;
        }
        if (fileAbsolutePath==null) {
            log.error("upload file failed,please upload again!");
            return null;
        }
        String path=baseurl+ "/" + fileAbsolutePath[0]+ "/" +fileAbsolutePath[1];
        log.info("path:    --------->" + path);
        return path;
    }
}

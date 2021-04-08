package com.jonm.model.bean;

import lombok.Data;

/**
 * @program: blog-api
 * @description: 文件基础信息
 * @author: Jonm
 * @create: 2021-03-30 22:27
 **/
@Data
public class FastDFSFile {
    private String name;
    private byte[] content;
    private String ext;
    private String md5;
    private String author;

    public FastDFSFile(String name, byte[] content, String ext) {
        this.name = name;
        this.content = content;
        this.ext = ext;
    }
}

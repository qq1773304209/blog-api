package com.jonm.util.fastdfs;

import com.jonm.model.bean.FastDFSFile;
import lombok.extern.slf4j.Slf4j;
import org.csource.common.IniFileReader;
import org.csource.fastdfs.*;
import org.springframework.core.io.ClassPathResource;
import org.csource.common.NameValuePair;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @program: blog-api
 * @description: FastDFS上传工具类
 * @author: Jonm
 * @create: 2021-03-30 22:30
 **/
@Slf4j
public class FastDFSClient {
    private static TrackerClient trackerClient;
    private static StorageClient storageClient;
    private static TrackerServer trackerServer;
    private static StorageServer storageServer;

    static {
        try {
            //表示读取配置文件
            String filePath = new ClassPathResource("fdfs_client.conf").getFile().getAbsolutePath();
            ClientGlobal.init(filePath);
            trackerClient = new TrackerClient();
            trackerServer = trackerClient.getTrackerServer();
            //存储数据
            storageClient = new StorageClient(trackerServer, null);
        } catch (Exception e) {
            log.error("FastDFS Client Init Fail!",e);
        }
    }

    
    /**
    * @Description: 文件上传
    * @Param: [file]
    * @return: java.lang.String[]
    * @Author: Jonm
    * @Date: 2021/3/30
    */
    public static String[] upload(FastDFSFile file) {
        log.info("File Name: " + file.getName() + "File Length:" + file.getContent().length);

        NameValuePair[] meta_list = new NameValuePair[1];
        meta_list[0] = new NameValuePair("author", file.getAuthor());

        long startTime = System.currentTimeMillis();
        String[] uploadResults = null;
        try {
            storageClient = new StorageClient(trackerServer, storageServer);
            uploadResults = storageClient.upload_file(file.getContent(), file.getExt(), meta_list);
        } catch (IOException e) {
            log.error("IO Exception when uploadind the file:" + file.getName(), e);
            return null;
        } catch (Exception e) {
            log.error("Non IO Exception when uploadind the file:" + file.getName(), e);
            return null;
        }
        log.info("upload_file time used:" + (System.currentTimeMillis() - startTime) + " ms");

        if (uploadResults == null) {
            log.error("upload file fail, error code:" + storageClient.getErrorCode());
            return null;
        }
        String groupName = uploadResults[0];
        String remoteFileName = uploadResults[1];

        log.info("upload file successfully!!!" + "group_name:" + groupName + ", remoteFileName:" + " " + remoteFileName);
        return uploadResults;
    }



    /**
    * @Description: 下载文件
    * @Param: [groupName, remoteFileName]
    * @return: InputStream
    * @Author: Jonm
    * @Date: 2021/3/30
    */
    public static InputStream downFile(String groupName, String remoteFileName) {
        try {
            storageClient = new StorageClient(trackerServer, storageServer);
            byte[] fileByte = storageClient.download_file(groupName, remoteFileName);
            InputStream ins = new ByteArrayInputStream(fileByte);
            return ins;
        } catch (IOException e) {
            log.error("IO Exception: Get File from Fast DFS failed", e);
        } catch (Exception e) {
            log.error("Non IO Exception: Get File from Fast DFS failed", e);
        }
        return null;
    }

    
    /**
    * @Description: 根据groupName和文件名获取文件信息。
    * @Param: [groupName, remoteFileName]
    * @return: org.csource.fastdfs.FileInfo
    * @Author: Jonm
    * @Date: 2021/3/30
    */
    public static FileInfo getFile(String groupName, String remoteFileName) {
        try {
            storageClient = new StorageClient(trackerServer, storageServer);
            return storageClient.get_file_info(groupName, remoteFileName);
        } catch (IOException e) {
            log.error("IO Exception: Get File from Fast DFS failed", e);
        } catch (Exception e) {
            log.error("Non IO Exception: Get File from Fast DFS failed", e);
        }
        return null;
    }

}

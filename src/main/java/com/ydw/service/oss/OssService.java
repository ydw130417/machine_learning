package com.ydw.service.oss;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.PutObjectResult;
import com.ydw.common.YdwUtils;
import com.ydw.config.oss.OssProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.UUID;
import java.util.function.Function;

/**
 * oss
 *
 * @author HYL
 * @create 2018-01-15 下午2:15
 **/
@Service
public class OssService {

    @Autowired
    OSSClient client;

    @Autowired
    OssProperties ossProperties;

    /**
     * 判断题目Id是否存在
     * @param timuId
     * @return
     */
    public Boolean isHtmlExist(String timuId){
        String key="version5/template1/"+timuId+".html";
        return client.doesObjectExist(ossProperties.getHtmlEndPoint(), key);
    }

    /**
     * 判断文件是否存在
     */
    public boolean isFileExist(String fileName, String endPoint, Function<String,String> makeObjectKey){
        return client.doesObjectExist(endPoint,makeObjectKey.apply(fileName));
    }

    /**
     * 上传文件
     */
    public String uploadFile(File file,String oraName,String fileId){
        String url=null;
        if (file==null) {
            throw new RuntimeException("File is Null");
        }
        if (YdwUtils.isBlank(oraName)) {
            throw new RuntimeException("OraName is Blank");
        }
        if (file!=null) {
            String uploadFileName = getFileName(oraName);
            String machinePicDir = OssProperties.getMachinePicDir(fileId);
            try {
                String key = machinePicDir + uploadFileName;
                client.putObject(ossProperties.getPicPoint(), key, new FileInputStream(file));
                if (client.doesObjectExist(ossProperties.getPicPoint(), key)) {
                    url="http://"+ossProperties.getPicPoint()+"."+ossProperties.getEndPoint().replaceFirst("http://","")+"/"+key;
                }else {
                    throw new RuntimeException("文件上传失败");
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        return url;
    }

    /**
     * 获取文件名
     */
    public String getFileName(String name){
        UUID uuid = UUID.randomUUID();
        if (YdwUtils.isBlank(name)) {
            throw new RuntimeException("The fileName is Blank");
        }
        return uuid+name;
    }



}

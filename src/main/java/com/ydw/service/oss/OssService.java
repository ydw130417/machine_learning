package com.ydw.service.oss;

import com.aliyun.oss.OSSClient;
import com.ydw.config.oss.OssProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Boolean isHtmlExist(String timuId){
        String key="version5/template1/"+timuId+".html";
        return client.doesObjectExist(ossProperties.getHtmlEndPoint(), key);
    }


}

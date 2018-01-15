package com.ydw.config.oss;

import com.aliyun.oss.OSSClient;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 阿里云oss的相关配置文件
 *
 * @author HYL
 * @create 2018-01-15 下午2:02
 **/
@Configuration
@ConfigurationProperties("aliyun.oss")
public class OssProperties {
    private String access_id;
    private String access_key;
    private String img_url;
    private String bucket_name;
    private String endPoint;
    private String htmlEndPoint;

    public String getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(String endPoint) {
        this.endPoint = endPoint;
    }

    public String getHtmlEndPoint() {
        return htmlEndPoint;
    }

    public void setHtmlEndPoint(String htmlEndPoint) {
        this.htmlEndPoint = htmlEndPoint;
    }

    public String getAccess_id() {
        return access_id;
    }

    public void setAccess_id(String access_id) {
        this.access_id = access_id;
    }

    public String getAccess_key() {
        return access_key;
    }

    public void setAccess_key(String access_key) {
        this.access_key = access_key;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getBucket_name() {
        return bucket_name;
    }

    public void setBucket_name(String bucket_name) {
        this.bucket_name = bucket_name;
    }

    @Bean
    public OSSClient getOSSClient(){
        return new OSSClient(endPoint, access_id, access_key);
    }
}

package com.ydw.config.ocr;

import com.baidu.aip.ocr.AipOcr;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by ydw on 2018/1/12.
 */

@Configuration
public class BaiduOcrConfig {
    @Value("${baidu.app.id}")
    private String APPID;
    @Value("${baidu.api.key}")
    private String KEY;
    @Value("${baidu.secret.key}")
    private String SECRET;

    @Bean
    public AipOcr getAipOcr() {
        return new AipOcr(APPID, KEY, SECRET);
    }

    public String getAPPID() {
        return APPID;
    }

    public void setAPPID(String APPID) {
        this.APPID = APPID;
    }

    public String getKEY() {
        return KEY;
    }

    public void setKEY(String KEY) {
        this.KEY = KEY;
    }

    public String getSECRET() {
        return SECRET;
    }

    public void setSECRET(String SECRET) {
        this.SECRET = SECRET;
    }

}

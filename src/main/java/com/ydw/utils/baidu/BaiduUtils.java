package com.ydw.utils.baidu;

import com.baidu.aip.ocr.AipOcr;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.HashMap;

/**
 * Created by ydw on 2018/1/12.
 */
@Service
public class BaiduUtils {

    @Autowired
    AipOcr aipOcr;

    /**
     *传入base64的图片编码或者是本地的路径,进行图片的识别
     * @param imgPathOrBase64Code
     * @return
     */
    public String getContentByImgPath(String imgPathOrBase64Code){
        String content=null;
        StringBuilder stringBuilder=new StringBuilder();
        try {
            if (imgPathOrBase64Code != null) {
                File file = new File(imgPathOrBase64Code);
                if (file.exists()) {
                    //进行百度接口的调用
                    JSONObject response = aipOcr.basicGeneral(imgPathOrBase64Code, new HashMap<String, String>());
                    JSONArray jsonArray = response.getJSONArray("words_result");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        String words = jsonArray.getJSONObject(i).getString("words");
                        stringBuilder.append(words);
                    }
                    content=stringBuilder.toString();
                }
            }
        } catch (Exception e) {
           e.printStackTrace();
        }
        return content;
    }
}

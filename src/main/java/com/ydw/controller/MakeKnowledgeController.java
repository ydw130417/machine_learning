package com.ydw.controller;

import com.ydw.model.es_model.TimuDocument;
import com.ydw.model.jpa_model.Base_timu_search;
import com.ydw.repository.es_repository.TimuDocumentRepository;
import com.ydw.repository.jap_repository.BaseTimuSearchRepository;
import com.ydw.utils.baidu.BaituUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Optional;

/**
 * Created by ydw on 2018/1/12.
 */
@Controller
@RequestMapping("/make")
public class MakeKnowledgeController {
    @Autowired
    BaseTimuSearchRepository baseTimuSearchRepository;

    @Autowired
    BaituUtils baituUtils;

    @Autowired
    TimuDocumentRepository timuDocumentRepository;


    @PostMapping("/findTimu")
    public @ResponseBody String findTimu(String words){
        String htmlUrl="http://queshtml.51sprint.com/version5/template1/";
        Base_timu_search base_timu_search = baseTimuSearchRepository.findFirstByTrunkContains(words);
        String id = base_timu_search.getId();
        return htmlUrl+id+".html";
    }


    @PostMapping("/upload")
    public @ResponseBody String upLoadFiles(MultipartFile first, HttpServletRequest request,String timuId){
        String realPath = request.getSession().getServletContext().getRealPath("/");
        System.out.println(realPath);
        String templePath="/Users/mac/Pictures/uplad/";
        String filename = first.getOriginalFilename();
        try {
            File templeFile = new File(templePath + filename);
            first.transferTo(templeFile);
            String contentByImgPath = baituUtils.getContentByImgPath(templeFile.getAbsolutePath());
            Optional<TimuDocument> byId = timuDocumentRepository.findById(timuId);
            if (byId.isPresent()) {
                //如果题目存在则更新第一个搜索内容
                byId.get().setFirstContent(contentByImgPath);
                timuDocumentRepository.save(byId.get());
            }else {
                //如果这个文档不存在,先去数据库查询所有的信息,完善Document然后执行保存
                System.out.println("完善题目信息,然后进行保存索引");

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  "upload";
    }

}

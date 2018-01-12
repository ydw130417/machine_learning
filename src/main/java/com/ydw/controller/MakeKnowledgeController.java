package com.ydw.controller;

import com.ydw.config.upload.StorageService;
import com.ydw.model.jpa_model.Base_timu_search;
import com.ydw.repository.jap_repository.BaseTimuSearchRepository;
import com.ydw.utils.baidu.BaituUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;

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


    @PostMapping("/findTimu")
    public @ResponseBody String findTimu(String words){
        String htmlUrl="http://queshtml.51sprint.com/version5/template1/";
        Base_timu_search base_timu_search = baseTimuSearchRepository.findFirstByTrunkContains(words);
        String id = base_timu_search.getId();
        return htmlUrl+id+".html";
    }


    @PostMapping("/upload")
    public @ResponseBody String upLoadFiles(MultipartFile first){
        System.out.println(first.getOriginalFilename());
//        System.out.println("...");
//        System.out.println(first.getOriginalFilename());
        return  "upload";
    }

}

package com.ydw.controller;

import com.ydw.common.YdwUtils;
import com.ydw.model.es_model.TimuDocument;
import com.ydw.model.jpa_model.Base_timu_search;
import com.ydw.model.para.Make_File;
import com.ydw.repository.es_repository.TimuDocumentRepository;
import com.ydw.repository.jap_repository.BaseTimuSearchRepository;
import com.ydw.service.MakeService;
import com.ydw.utils.baidu.BaituUtils;
import com.ydw.utils.es_query.EsQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
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

    @Autowired
    TimuDocumentRepository timuDocumentRepository;

    @Autowired
    EsQueryService esQueryService;

    @Autowired
    MakeService makeService;


    @PostMapping("/findTimu")
    public @ResponseBody String findTimu(String words){
        String htmlUrl="http://queshtml.51sprint.com/version5/template1/";
        Base_timu_search base_timu_search = baseTimuSearchRepository.findFirstByTrunkContains(words);
        String id = base_timu_search.getId();
        return htmlUrl+id+".html";
    }


    @PostMapping("/upload")
    public @ResponseBody String upLoadFiles(MultipartFile first, MultipartFile second,MultipartFile third,String timuId,HttpServletRequest request){
        String message="";
        if (timuId!=null&&!timuId.equalsIgnoreCase("")) {
            System.out.println(timuId);
            Make_File make_file = new Make_File();
            YdwUtils.filterNull(timuId,make_file,(x,y)->y.setTimuId(x));
            YdwUtils.filterNull(first,make_file,(x,y)->y.setFist(x));
            YdwUtils.filterNull(second,make_file,(x,y)->y.setSecond(x));
            YdwUtils.filterNull(third,make_file,(x,y)->y.setThird(x));
            System.out.println(make_file);
            String templePath="/Users/mac/Pictures/uplad/";
            boolean b = makeService.makeDocumentInfo(make_file, templePath);
            String s = b ? "success" : "fail";
            message=s;
        }else {
            message="请先完成题目的查询";
        }
        return message;
    }



    /**
     * multyQuery
     * @param keyWords
     * @return
     */
    @GetMapping("/searchTest")
    @ResponseBody
    public String search(String keyWords){
        List<TimuDocument> timusByKeyWords = esQueryService.findTimusByKeyWords(keyWords);
        return "ok";
    }


    /**
     * booleanQuery
     * @param keyWords
     * @return
     */
    @GetMapping("/searchByBoolean")
    @ResponseBody
    public List<TimuDocument> searchByBoolean(String keyWords){
        Page<TimuDocument> documentsByBoolean = esQueryService.findDocumentsByBoolean(keyWords);
        return documentsByBoolean.getContent();
    }
}

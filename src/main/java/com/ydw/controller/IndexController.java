package com.ydw.controller;

import com.alibaba.fastjson.JSON;
import com.ydw.model.es_model.TimuDocument;
import com.ydw.model.jpa_model.Base_timu_search;
import com.ydw.repository.es_repository.TimuDocumentRepository;
import com.ydw.repository.jap_repository.BaseTimuSearchRepository;
import com.ydw.utils.es_query.EsQueryService;
import org.elasticsearch.client.transport.TransportClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by ydw on 2018/1/10.
 */
@RequestMapping("/")
@Controller
public class IndexController {

    @Autowired
    BaseTimuSearchRepository baseTimuSearchRepository;

    @Autowired
    TimuDocumentRepository timuDocumentRepository;

    @Autowired
    TransportClient client;

    @Autowired
    private EsQueryService esQueryService;


    @RequestMapping("/page")
    public @ResponseBody
    List<Base_timu_search> findByPage(Integer index, Integer limit) {
        return baseTimuSearchRepository.findAll(PageRequest.of(index,limit)).getContent();
    }

    @RequestMapping("/batchSave")
    public @ResponseBody
    Iterable<TimuDocument> batchSave(Integer index, Integer limit) {
        return timuDocumentRepository.findAll();
    }

    @RequestMapping("/findByKeyWords")
    public @ResponseBody Object findByKeyWords(String words) {
        System.out.println(words);
        Page<TimuDocument> documentsByPage = esQueryService.findDocumentsByPage(words, PageRequest.of(1,5));
        return JSON.toJSON(documentsByPage);
    }

    @RequestMapping("/findMath")
    public @ResponseBody
    Page<Base_timu_search> findMath(Integer index, Integer pageSize){
        return baseTimuSearchRepository.findBase_timu_searchesBySubjectIdEquals(2L, PageRequest.of(index,pageSize));
    }
}

package com.ydw.controller;

import com.alibaba.fastjson.JSON;
import com.ydw.model.es_model.TimuDocument;
import com.ydw.model.jpa_model.Base_timu_search;
import com.ydw.repository.es_repository.TimuDocumentRepository;
import com.ydw.repository.jap_repository.BaseTimuSearchRepository;
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


    @RequestMapping("/page")
    public @ResponseBody
    List<Base_timu_search> findByPage(Integer index, Integer limit) {
        PageRequest pageRequest = new PageRequest(index, limit);
        return baseTimuSearchRepository.findAll(pageRequest).getContent();
    }

    @RequestMapping("/batchSave")
    public @ResponseBody
    Iterable<TimuDocument> batchSave(Integer index, Integer limit) {
        timuDocumentRepository.deleteAll();
        limit = limit == null ? 10 : limit;
        index = index == null ? 1 : index;
        findByPage(index, limit).parallelStream().forEach(base_timu_search -> {
            TimuDocument timuDocument = new TimuDocument();
            String searchContent = base_timu_search.getTrunk() + base_timu_search.getInput_choice_json();
            timuDocument.setFirstContent(searchContent);
            timuDocument.setId(base_timu_search.getId());
            TimuDocument save = timuDocumentRepository.save(timuDocument);
            System.out.println(save);
        });
        return timuDocumentRepository.findAll();
    }

    @RequestMapping("/findByKeyWords")
    public @ResponseBody
    Object findByKeyWords(String words) {
        System.out.println(words);
//        SearchResponse searchResponse = client.prepareSearch("lesprint")
//                .setTypes("timu")
//                .setQuery(
//                        QueryBuilders.boolQuery()
//                                .must(QueryBuilders.matchQuery("firstContent", words))
//                )
//                .setFrom(0)
//                .setSize(5)
//                .setExplain(false)
//                .get();
//        SearchHit[] hits = searchResponse.getHits().getHits();
        Page<TimuDocument> timuDocumentsByFirstContentLike = timuDocumentRepository.findTimuDocumentsByFirstContentContains(words, new PageRequest(1, 5));
        List<TimuDocument> timuDocumentsByFirstContentContaining = timuDocumentRepository.findTimuDocumentsByFirstContentLike(words);
        Page<TimuDocument> timuDocumentsByFirstContentLike1 = timuDocumentRepository.findTimuDocumentsByFirstContentLike(words, new PageRequest(1, 5));
        return JSON.toJSON(timuDocumentsByFirstContentLike1);
    }

    @RequestMapping("/findMath")
    public @ResponseBody
    Page<Base_timu_search> findMath(Integer index, Integer pageSize){
        return baseTimuSearchRepository.findBase_timu_searchesBySubjectIdEquals(2L, new PageRequest(index, pageSize));
    }
}

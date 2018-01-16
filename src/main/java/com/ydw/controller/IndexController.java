package com.ydw.controller;

import com.alibaba.fastjson.JSON;
import com.ydw.model.es_model.TimuDocument;
import com.ydw.model.jpa_model.Base_timu_search;
import com.ydw.model.jpa_model.Machine;
import com.ydw.repository.es_repository.TimuDocumentRepository;
import com.ydw.repository.jap_repository.BaseTimuSearchRepository;
import com.ydw.repository.jap_repository.MachineRepository;
import com.ydw.service.oss.OssService;
import com.ydw.utils.es_query.EsQueryService;
import org.elasticsearch.client.transport.TransportClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;

/**
 * Created by ydw on 2018/1/10.
 */
@RequestMapping("/")
@Controller
@Transactional
public class IndexController {

    @Autowired
    BaseTimuSearchRepository baseTimuSearchRepository;

    @Autowired
    TimuDocumentRepository timuDocumentRepository;

    @Autowired
    TransportClient client;

    @Autowired
    private EsQueryService esQueryService;

    @Autowired
    private OssService ossService;

    @Autowired
    private MachineRepository machineRepository;


    @RequestMapping("/page")
    public @ResponseBody
    List<Base_timu_search> findByPage(Integer index, Integer limit) {
        return baseTimuSearchRepository.findAll(PageRequest.of(index, limit)).getContent();
    }

    @RequestMapping("/batchSave")
    public @ResponseBody
    Iterable<TimuDocument> batchSave(Integer index, Integer limit) {
        return timuDocumentRepository.findAll();
    }

    @RequestMapping("/findByKeyWords")
    public @ResponseBody
    Object findByKeyWords(String words) {
        System.out.println(words);
        Page<TimuDocument> documentsByPage = esQueryService.findDocumentsByPage(words, PageRequest.of(1, 5));
        return JSON.toJSON(documentsByPage);
    }

    @RequestMapping("/findMath")
    public @ResponseBody
    Page<Base_timu_search> findMath(Integer index, Integer pageSize) {
        return baseTimuSearchRepository.findBase_timu_searchesBySubjectIdEquals(2L, PageRequest.of(index, pageSize));
    }

    @RequestMapping("/buildMath")
    public String buildMath() {
        Integer index = 1;
        Page<Base_timu_search> base_timu_searchesBySubjectIdEquals = baseTimuSearchRepository.findBase_timu_searchesBySubjectIdEquals(2L, PageRequest.of(index, 1000));
        List<Base_timu_search> content = base_timu_searchesBySubjectIdEquals.getContent();
        while (index<=50) {
            base_timu_searchesBySubjectIdEquals = baseTimuSearchRepository.findBase_timu_searchesBySubjectIdEquals(2L, PageRequest.of(index, 1000));
            content = base_timu_searchesBySubjectIdEquals.getContent();
            if (content.size() > 0) {
                content.parallelStream().forEach(x -> {
                    String id = x.getId();
                    Machine machine = new Machine(id);
                    if (ossService.isHtmlExist(id)) {
                        String trunk = x.getTrunk();
                        String input_choice_json = x.getInput_choice_json();
                        TimuDocument timuDocument = new TimuDocument(id);
                        timuDocument.setFirstContent(trunk + input_choice_json);
                        timuDocumentRepository.save(timuDocument);
                        machine.setTemplate1(1L);
                        machine.setInedex(1L);
                    }
                    machineRepository.save(machine);
                });
            }
            index++;
        }
        return "生成完成";
    }

    @GetMapping("/build/{id}")
    @ResponseBody
    public String buildById(@PathVariable String id){
        Optional<Base_timu_search> baseTimuSearch = baseTimuSearchRepository.findById(id);
        String message="ok";
        try {
            baseTimuSearch.ifPresent(x->{
                String timuId = x.getId();
                //判断是否已经生产了索引
                if (!timuDocumentRepository.existsById(timuId)) {
                    //没有产生索引,判断是否有对应的静态页
                    if (ossService.isHtmlExist(timuId)) {
                        //静态页面存在的话,然后才进行做索引
                        TimuDocument timuDocument = new TimuDocument(id);
                        String content = x.getTrunk() + x.getInput_choice_json();
                        timuDocument.setFirstContent(content);
                        timuDocumentRepository.save(timuDocument);
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            message="somethingWrong!";
        }
        return message;
    }
}

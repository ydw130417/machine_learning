package com.ydw.utils.es_query;

import com.ydw.config.es.EsProperties;
import com.ydw.model.es_model.TimuDocument;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 搜索内容
 *
 * @author HYL
 * @create 2018-01-14 下午8:36
 **/
@Service
public class EsQueryService {
    @Autowired
    private  EsProperties esProperties;



    @Autowired
    private TransportClient client;

    /**
     * 根据配置文件中的indexName,typeName进行搜索
     * @param keyWords
     * @return
     */
    public List<TimuDocument> findTimusByKeyWords(String keyWords){
                SearchResponse searchResponse = client.prepareSearch(esProperties.getIndexName())
                .setTypes(esProperties.getTypeName())
                .setQuery(
                        buildQuery(keyWords)
                )
                .setFrom(0)
                .setSize(5)
                .setExplain(false)
                .get();
        SearchHit[] hits = searchResponse.getHits().getHits();
        return null;
    }

    /**
     * 构建查询条件,根据配置文件中配置的字段
     * @param keyWords
     * @return
     */
    private QueryBuilder buildQuery(String keyWords){
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        List<String> searchFields = esProperties.getSearchFields();
        searchFields.stream().forEach(s -> QueryBuilders.matchQuery(s,keyWords));
        return boolQueryBuilder;
    }


}

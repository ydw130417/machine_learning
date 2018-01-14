package com.ydw.utils.es_query;

import com.ydw.config.es.EsProperties;
import com.ydw.model.es_model.TimuDocument;
import com.ydw.repository.es_repository.TimuDocumentRepository;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
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
    private TimuDocumentRepository timuDocumentRepository;


    @Autowired
    private TransportClient client;

    /**
     * 使用client进行查询
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
     * 更加简单的本地查询  bb d4 d56  cc  9ef
     */

    public Page<TimuDocument> findDocumentsByPage(String keyWords, PageRequest pageRequest){
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        nativeSearchQueryBuilder.withQuery(buildQuery(keyWords));
        // 不能够使用,如果分页的话则会把最佳的放在最后面:nativeSearchQueryBuilder .withPageable(pageRequest);
        NativeSearchQuery build = nativeSearchQueryBuilder.build();
        Page<TimuDocument> documents = timuDocumentRepository.search(build);
        return documents;

    }

    /**
     * 构建查询条件,根据配置文件中配置的字段
     * @param keyWords
     * @return
     */
    public QueryBuilder buildQuery(String keyWords){
        return QueryBuilders.multiMatchQuery(keyWords, esProperties.getSearchFields());
    }


}

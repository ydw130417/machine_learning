package com.ydw.utils.es_query;

import com.ydw.config.es.EsProperties;
import com.ydw.model.es_model.TimuDocument;
import com.ydw.repository.es_repository.TimuDocumentRepository;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 搜索内容
 *
 * @author HYL
 * @create 2018-01-14 下午8:36
 **/
@Service
public class EsQueryService {

    @Autowired
    private EsProperties esProperties;

    @Autowired
    private TimuDocumentRepository timuDocumentRepository;


    @Autowired
    private TransportClient client;

    /**
     * 使用client进行查询
     *
     * @param keyWords
     * @return
     */
    public List<TimuDocument> findTimusByKeyWords(String keyWords) {
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
     * 更加简单的本地查询  这边采用的是多字段查询
     */

    public Page<TimuDocument> findDocumentsByPage(String keyWords) {
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        nativeSearchQueryBuilder.withQuery(buildQuery(keyWords));
        // 不能够使用,如果分页的话则会把最佳的放在最后面:nativeSearchQueryBuilder .withPageable(pageRequest);
        NativeSearchQuery build = nativeSearchQueryBuilder.build();
        Page<TimuDocument> documents = timuDocumentRepository.search(build);
        return documents;

    }

    /**
     * 这边使用的是boolean查询,使用should来构造查询条件
     */

    public Page<TimuDocument> findDocumentsByBoolean(String keyWords) {
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        nativeSearchQueryBuilder.withQuery(buildBooleanQuery(keyWords));
        NativeSearchQuery build = nativeSearchQueryBuilder.build();
        return timuDocumentRepository.search(build);
    }

    public List<TimuDocument> findByWords(String words, QueryBuilder queryBuilder) {
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        nativeSearchQueryBuilder.withQuery(queryBuilder);
        NativeSearchQuery build = nativeSearchQueryBuilder.build();
        return timuDocumentRepository.search(build).getContent();
    }

    /**
     * 构建查询条件,根据配置文件中配置的字段,multyQuery
     *
     * @param keyWords
     * @return
     */
    public QueryBuilder buildQuery(String keyWords) {
        return QueryBuilders.multiMatchQuery(keyWords, esProperties.getSearchFields());
    }

    /**
     * boolean 查询
     */
    public QueryBuilder buildBooleanQuery(String keyWords) {
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        Arrays.stream(esProperties.getSearchFields()).forEach(x -> boolQueryBuilder.should(QueryBuilders.matchQuery(x, keyWords)));
        return boolQueryBuilder;
    }

    /**
     * 多个字段进行查询,然后取交集,返回一个List
     */
    public List<TimuDocument> findFinalRestult(String words) {
        List<TimuDocument> first = findByWords(words, QueryBuilders.matchQuery(esProperties.getSearchFields()[0], words));
        List<String> second = findByWords(words, QueryBuilders.matchQuery(esProperties.getSearchFields()[1], words)).stream().map(TimuDocument::getId).collect(Collectors.toList());
        List<String> third = findByWords(words, QueryBuilders.matchQuery(esProperties.getSearchFields()[2], words)).stream().map(TimuDocument::getId).collect(Collectors.toList());
        List<TimuDocument> result = first.stream().filter(x -> second.contains(x.getId()) || third.contains(x.getId())
        ).collect(Collectors.toList());
        if (result.size()==0) {
            result.add(first.get(0));
            result.add(timuDocumentRepository.findById(second.get(0)).get());
            result.add(timuDocumentRepository.findById(third.get(0)).get());
            result.add(first.get(1));
            result.add(first.get(2));
        }
        return result.stream().distinct().collect(Collectors.toList());
    }


}

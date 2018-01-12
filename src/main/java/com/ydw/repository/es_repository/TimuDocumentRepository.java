package com.ydw.repository.es_repository;

import com.ydw.model.es_model.TimuDocument;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * Created by ydw on 2018/1/10.
 */
public interface TimuDocumentRepository extends ElasticsearchRepository<TimuDocument,String> {


     Page<TimuDocument> findTimuDocumentsByFirstContentLike(String words, Pageable pageable);
     Page<TimuDocument> findTimuDocumentsByFirstContentContains(String words, Pageable pageable);
     List<TimuDocument> findTimuDocumentsByFirstContentLike(String words);

}

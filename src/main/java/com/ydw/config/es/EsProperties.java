package com.ydw.config.es;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author HYL
 * @create 2018-01-14 下午8:41
 **/
@Component
@ConfigurationProperties("es")
public class EsProperties {
    private String indexName;
    private String typeName;
    private List<String> searchFields;

    public String getIndexName() {
        return indexName;
    }

    public void setIndexName(String indexName) {
        this.indexName = indexName;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public List<String> getSearchFields() {
        return searchFields;
    }

    public void setSearchFields(List<String> searchFields) {
        this.searchFields = searchFields;
    }
}

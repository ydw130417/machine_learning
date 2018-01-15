package com.ydw.model.es_model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * Created by ydw on 2018/1/10.
 */
@Document(indexName = "lesprint", type = "timu")
public class TimuDocument {
    @Id
    private String id;
    @Field(type = FieldType.text, index = true, searchAnalyzer = "ik_max_word", analyzer = "ik_max_word")
    private String firstContent;
    @Field(type = FieldType.text, index = true, searchAnalyzer = "ik_max_word", analyzer = "ik_max_word")
    private String secondContent;
    @Field(type = FieldType.text, index = true, searchAnalyzer = "ik_max_word", analyzer = "ik_max_word")
    private String thirdContent;
    @Field(type = FieldType.text, index = true, searchAnalyzer = "ik_max_word", analyzer = "ik_max_word")
    private String fouthContent;
    @Field(type = FieldType.text, index = true, searchAnalyzer = "ik_max_word", analyzer = "ik_max_word")
    private String firveContent;

    public TimuDocument() {
    }

    public TimuDocument(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstContent() {
        return firstContent;
    }

    public void setFirstContent(String firstContent) {
        this.firstContent = firstContent;
    }

    public String getSecondContent() {
        return secondContent;
    }

    public void setSecondContent(String secondContent) {
        this.secondContent = secondContent;
    }

    public String getThirdContent() {
        return thirdContent;
    }

    public void setThirdContent(String thirdContent) {
        this.thirdContent = thirdContent;
    }

    public String getFouthContent() {
        return fouthContent;
    }

    public void setFouthContent(String fouthContent) {
        this.fouthContent = fouthContent;
    }

    public String getFirveContent() {
        return firveContent;
    }

    public void setFirveContent(String firveContent) {
        this.firveContent = firveContent;
    }

    @Override
    public String toString() {
        return "TimuDocument{" +
                "id='" + id + '\'' +
                ", firstContent='" + firstContent + '\'' +
                ", secondContent='" + secondContent + '\'' +
                ", thirdContent='" + thirdContent + '\'' +
                ", fouthContent='" + fouthContent + '\'' +
                ", firveContent='" + firveContent + '\'' +
                '}';
    }
}

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {return true;}
        if (o == null || getClass() != o.getClass()) {return false;}

        TimuDocument that = (TimuDocument) o;

        if (!id.equals(that.id)) {return false;}
        if (firstContent != null ? !firstContent.equals(that.firstContent) : that.firstContent != null) {return false;}
        if (secondContent != null ? !secondContent.equals(that.secondContent) : that.secondContent != null)
        {return false;}
        if (thirdContent != null ? !thirdContent.equals(that.thirdContent) : that.thirdContent != null) {return false;}
        if (fouthContent != null ? !fouthContent.equals(that.fouthContent) : that.fouthContent != null) {return false;}
        return firveContent != null ? firveContent.equals(that.firveContent) : that.firveContent == null;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + (firstContent != null ? firstContent.hashCode() : 0);
        result = 31 * result + (secondContent != null ? secondContent.hashCode() : 0);
        result = 31 * result + (thirdContent != null ? thirdContent.hashCode() : 0);
        result = 31 * result + (fouthContent != null ? fouthContent.hashCode() : 0);
        result = 31 * result + (firveContent != null ? firveContent.hashCode() : 0);
        return result;
    }
}

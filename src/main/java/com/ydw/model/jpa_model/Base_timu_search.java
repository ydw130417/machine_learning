package com.ydw.model.jpa_model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class Base_timu_search {
  @Id
  @GeneratedValue(generator = "jpa-uuid")
  @Column(length = 32)
  private String id;
  @Column(name = "subject_id")
  private Long subjectId;
  private Long timu_type_id;
  private String province_ids;
  private String city_ids;
  private String area_ids;
  private Long time;
  private Double scroe;
  private Long grade_id;
  private Long year_id;
  private String parent_id;
  private String video_code;
  private Long source;
  private Long is_expand_practice;
  private Long is_have_child;
  private String picture_json;
  private Long difficult_level;
  private String trunk;
  private String analysis;
  private String answer;
  private String comment;
  private String input_json;
  private String input_choice_json;
  private Long main_knowledge_id;
  private Long sort;
  private java.sql.Timestamp create_time;
  private Long school_degree_id;
  private java.sql.Timestamp update_time;
  private String aliyun_video_code;
  private Long is_execute;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Long getSubjectId() {
    return subjectId;
  }

  public void setSubjectId(Long subjectId) {
    this.subjectId = subjectId;
  }

  public Long getTimu_type_id() {
    return timu_type_id;
  }

  public void setTimu_type_id(Long timu_type_id) {
    this.timu_type_id = timu_type_id;
  }

  public String getProvince_ids() {
    return province_ids;
  }

  public void setProvince_ids(String province_ids) {
    this.province_ids = province_ids;
  }

  public String getCity_ids() {
    return city_ids;
  }

  public void setCity_ids(String city_ids) {
    this.city_ids = city_ids;
  }

  public String getArea_ids() {
    return area_ids;
  }

  public void setArea_ids(String area_ids) {
    this.area_ids = area_ids;
  }

  public Long getTime() {
    return time;
  }

  public void setTime(Long time) {
    this.time = time;
  }

  public Double getScroe() {
    return scroe;
  }

  public void setScroe(Double scroe) {
    this.scroe = scroe;
  }

  public Long getGrade_id() {
    return grade_id;
  }

  public void setGrade_id(Long grade_id) {
    this.grade_id = grade_id;
  }

  public Long getYear_id() {
    return year_id;
  }

  public void setYear_id(Long year_id) {
    this.year_id = year_id;
  }

  public String getParent_id() {
    return parent_id;
  }

  public void setParent_id(String parent_id) {
    this.parent_id = parent_id;
  }

  public String getVideo_code() {
    return video_code;
  }

  public void setVideo_code(String video_code) {
    this.video_code = video_code;
  }

  public Long getSource() {
    return source;
  }

  public void setSource(Long source) {
    this.source = source;
  }

  public Long getIs_expand_practice() {
    return is_expand_practice;
  }

  public void setIs_expand_practice(Long is_expand_practice) {
    this.is_expand_practice = is_expand_practice;
  }

  public Long getIs_have_child() {
    return is_have_child;
  }

  public void setIs_have_child(Long is_have_child) {
    this.is_have_child = is_have_child;
  }

  public String getPicture_json() {
    return picture_json;
  }

  public void setPicture_json(String picture_json) {
    this.picture_json = picture_json;
  }

  public Long getDifficult_level() {
    return difficult_level;
  }

  public void setDifficult_level(Long difficult_level) {
    this.difficult_level = difficult_level;
  }

  public String getTrunk() {
    return trunk;
  }

  public void setTrunk(String trunk) {
    this.trunk = trunk;
  }

  public String getAnalysis() {
    return analysis;
  }

  public void setAnalysis(String analysis) {
    this.analysis = analysis;
  }

  public String getAnswer() {
    return answer;
  }

  public void setAnswer(String answer) {
    this.answer = answer;
  }

  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }

  public String getInput_json() {
    return input_json;
  }

  public void setInput_json(String input_json) {
    this.input_json = input_json;
  }

  public String getInput_choice_json() {
    return input_choice_json;
  }

  public void setInput_choice_json(String input_choice_json) {
    this.input_choice_json = input_choice_json;
  }

  public Long getMain_knowledge_id() {
    return main_knowledge_id;
  }

  public void setMain_knowledge_id(Long main_knowledge_id) {
    this.main_knowledge_id = main_knowledge_id;
  }

  public Long getSort() {
    return sort;
  }

  public void setSort(Long sort) {
    this.sort = sort;
  }

  public java.sql.Timestamp getCreate_time() {
    return create_time;
  }

  public void setCreate_time(java.sql.Timestamp create_time) {
    this.create_time = create_time;
  }

  public Long getSchool_degree_id() {
    return school_degree_id;
  }

  public void setSchool_degree_id(Long school_degree_id) {
    this.school_degree_id = school_degree_id;
  }

  public java.sql.Timestamp getUpdate_time() {
    return update_time;
  }

  public void setUpdate_time(java.sql.Timestamp update_time) {
    this.update_time = update_time;
  }

  public String getAliyun_video_code() {
    return aliyun_video_code;
  }

  public void setAliyun_video_code(String aliyun_video_code) {
    this.aliyun_video_code = aliyun_video_code;
  }

  public Long getIs_execute() {
    return is_execute;
  }

  public void setIs_execute(Long is_execute) {
    this.is_execute = is_execute;
  }
}

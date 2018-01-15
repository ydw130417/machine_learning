package com.ydw.model.jpa_model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Machine {
  @Id
  private String timuid;
  private Long template1=0L;
  private Long inedex=0L;

  public Machine(String timuid) {
    this.timuid = timuid;
  }


  public String getTimuid() {
    return timuid;
  }

  public void setTimuid(String timuid) {
    this.timuid = timuid;
  }

  public Long getTemplate1() {
    return template1;
  }

  public void setTemplate1(Long template1) {
    this.template1 = template1;
  }

  public Long getInedex() {
    return inedex;
  }

  public void setInedex(Long inedex) {
    this.inedex = inedex;
  }
}

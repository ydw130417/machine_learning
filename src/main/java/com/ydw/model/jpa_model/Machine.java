package com.ydw.model.jpa_model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Machine {
  @Id
  private String timuid;
  private Long template1;
  private Long inedex;
  private String firstpic;
  private String secondpic;
  private String thirdpic;
  private String firstcontent;
  private String secondcontent;
  private String thirdcontent;
  private String firstmapping;
  private String secondmapping;
  private String thirdmapping;

  public Machine() {
  }

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

  public String getFirstpic() {
    return firstpic;
  }

  public void setFirstpic(String firstpic) {
    this.firstpic = firstpic;
  }

  public String getSecondpic() {
    return secondpic;
  }

  public void setSecondpic(String secondpic) {
    this.secondpic = secondpic;
  }

  public String getThirdpic() {
    return thirdpic;
  }

  public void setThirdpic(String thirdpic) {
    this.thirdpic = thirdpic;
  }

  public String getFirstcontent() {
    return firstcontent;
  }

  public void setFirstcontent(String firstcontent) {
    this.firstcontent = firstcontent;
  }

  public String getSecondcontent() {
    return secondcontent;
  }

  public void setSecondcontent(String secondcontent) {
    this.secondcontent = secondcontent;
  }

  public String getThirdcontent() {
    return thirdcontent;
  }

  public void setThirdcontent(String thirdcontent) {
    this.thirdcontent = thirdcontent;
  }

  public String getFirstmapping() {
    return firstmapping;
  }

  public void setFirstmapping(String firstmapping) {
    this.firstmapping = firstmapping;
  }

  public String getSecondmapping() {
    return secondmapping;
  }

  public void setSecondmapping(String secondmapping) {
    this.secondmapping = secondmapping;
  }

  public String getThirdmapping() {
    return thirdmapping;
  }

  public void setThirdmapping(String thirdmapping) {
    this.thirdmapping = thirdmapping;
  }

  @Override
  public String toString() {
    return "Machine{" +
            "timuid='" + timuid + '\'' +
            ", template1=" + template1 +
            ", inedex=" + inedex +
            ", firstpic='" + firstpic + '\'' +
            ", secondpic='" + secondpic + '\'' +
            ", thirdpic='" + thirdpic + '\'' +
            ", firstcontent='" + firstcontent + '\'' +
            ", secondcontent='" + secondcontent + '\'' +
            ", thirdcontent='" + thirdcontent + '\'' +
            ", firstmapping='" + firstmapping + '\'' +
            ", secondmapping='" + secondmapping + '\'' +
            ", thirdmapping='" + thirdmapping + '\'' +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {return true;}
    if (o == null || getClass() != o.getClass()) {return false;}

    Machine machine = (Machine) o;

    return timuid.equals(machine.timuid);
  }

  @Override
  public int hashCode() {
    return timuid.hashCode();
  }
}

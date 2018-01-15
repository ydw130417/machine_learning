package com.ydw.model.para;

import org.springframework.web.multipart.MultipartFile;

/**
 * 用来接收参数的模型
 * @author HYL
 * @create 2018-01-15 上午10:11
 **/
public class Make_File {
    /**
     * 题目id
     */
    private String timuId;
    private MultipartFile fist;
    private MultipartFile second;
    private MultipartFile third;

    public String getTimuId() {
        return timuId;
    }

    public void setTimuId(String timuId) {
        this.timuId = timuId;
    }

    public MultipartFile getFist() {
        return fist;
    }

    public void setFist(MultipartFile fist) {
        this.fist = fist;
    }

    public MultipartFile getSecond() {
        return second;
    }

    public void setSecond(MultipartFile second) {
        this.second = second;
    }

    public MultipartFile getThird() {
        return third;
    }

    public void setThird(MultipartFile third) {
        this.third = third;
    }

    @Override
    public String toString() {
        return "Make_File{" +
                "timuId='" + timuId + '\'' +
                ", fist=" +(fist==null?"": fist.getOriginalFilename()) +
                ", second=" +(second==null?"": second.getOriginalFilename()) +
                ", third=" +(third==null?"": third.getOriginalFilename()) +
                '}';
    }
}

package com.ydw.common;

import com.ydw.function.TwoParamConsumer;

/**
 * @author HYL
 * @create 2018-01-15 下午12:50
 **/
public class YdwUtils {
    public  static <T,R> void filterNull(T t, R r , TwoParamConsumer<T,R> consumer){
        if (t != null) {
            consumer.accept(t,r);
        }
    }

    /**
     * 非空判断
     * @param str
     * @return
     */
    public static Boolean isBlank(String str){
        return str==null||str.equals("");
    }
}

package com.ydw.function;

/**
 * @author HYL
 * @create 2018-01-15 上午11:20
 **/
@FunctionalInterface
public interface TwoParamConsumer<T,R> {
    void accept(T t,R r);
}

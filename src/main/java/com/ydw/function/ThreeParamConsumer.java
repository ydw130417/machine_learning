package com.ydw.function;

/**
 * Created by ydw on 2018/1/17.
 */
@FunctionalInterface
public interface ThreeParamConsumer<T,R,Z> {
    void accept(T t,R r,Z z);
}

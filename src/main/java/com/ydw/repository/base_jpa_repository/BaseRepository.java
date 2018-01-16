package com.ydw.repository.base_jpa_repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author HYL
 * @create 2018-01-16 下午4:04
 **/
@NoRepositoryBean
public interface BaseRepository<T,ID> extends JpaRepository<T,ID> {
    /**
     * 获取一个实例对象,如果存在就返回存在的结果,如果不存在就返回一个新的实例
     * @param Id
     * @return
     */
    default T newInstance(T t,Object Id) throws IllegalAccessException, InstantiationException {
        Optional<T> byId = findById((ID) Id);
        if (byId.isPresent()) {
            return byId.get();
        }else {
            //返回一个新的实例
            T t1 = (T) t.getClass().newInstance();
            List<Field> collect = Arrays.stream(t1.getClass().getDeclaredFields()).filter(x -> x.isAnnotationPresent(javax.persistence.Id.class)).collect(Collectors.toList());
            collect.stream().findFirst().ifPresent(field -> {
                field.setAccessible(true);
                try {
                    field.set(t1,Id);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            });
            return t1;
        }
    }
}

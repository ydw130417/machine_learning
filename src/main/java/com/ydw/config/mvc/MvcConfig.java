package com.ydw.config.mvc;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by ydw on 2018/1/12.
 */
@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {
    /**
     * 页面跳转的控制
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
    }
}

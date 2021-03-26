package com.wyx.blog.config;

import com.wyx.blog.handler.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration  //该注解定义此类为配置类（即之前的xml配置文件）
public class InterceptorConfig implements WebMvcConfigurer {
    //类似于之前的mvc:interceptors,里面可以声明多个拦截器
    @Override   //重写这个方法添加拦截器
    public void addInterceptors(InterceptorRegistry registry) {
        //要拦截user下的所有访问请求,必须用户登录后才能访问
        //但是这样拦截的路径中有一些是不需要用户登陆也可访问额
        //一个*表示一级目录，两个*表示下面一级及其子目录，也就是下面所有的文件
        String[] addPathPatterns={"/admin/**"};  //需要拦截的类型的数组

        //要排除的路径，说明该路径不登陆也可以访问
        String[] excludePathPattern={"/admin","/admin/login"};  //需要排除的路径

        //下列方法具体为一个拦截器，类似于之前的mvc:interceptor bean class="" ..
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns(addPathPatterns).excludePathPatterns(excludePathPattern);
    }
}

package com.weavus.studyweb.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.WebRequestInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.weavus.studyweb.Interceptor.UserInterceptor;

@Configuration
public class WebMvcConfig  implements WebMvcConfigurer {

     @Override
    public void addInterceptors(InterceptorRegistry registry) {
        WebRequestInterceptor userInterceptor = new UserInterceptor();
        registry.addWebRequestInterceptor(userInterceptor);
    }
    
}

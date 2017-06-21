package net.fnsco.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import net.fnsco.freamwork.spring.LoginInterceptor;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/*");
    }
    //    @Override
    //    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
    //        registry.addResourceHandler("/*.js/**").addResourceLocations("/ui/static/");
    //        registry.addResourceHandler("/*.css/**").addResourceLocations("/ui/static/");
    //    }
}
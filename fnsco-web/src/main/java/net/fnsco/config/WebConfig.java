package net.fnsco.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import net.fnsco.freamwork.spring.AppInterceptor;
import net.fnsco.freamwork.spring.LoginInterceptor;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    @Bean
    LoginInterceptor localInterceptor() {
        return new LoginInterceptor();
    }

    @Bean
    AppInterceptor appInterceptor() {
        return new AppInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localInterceptor()).addPathPatterns("/web/**");
        registry.addInterceptor(appInterceptor()).addPathPatterns("/app/**");
    }
    //    @Override
    //    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
    //        registry.addResourceHandler("/*.js/**").addResourceLocations("/ui/static/");
    //        registry.addResourceHandler("/*.css/**").addResourceLocations("/ui/static/");
    //    }
}

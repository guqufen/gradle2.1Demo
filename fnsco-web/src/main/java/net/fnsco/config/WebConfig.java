package net.fnsco.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;

import net.fnsco.freamwork.interceptor.AppInterceptor;
import net.fnsco.freamwork.interceptor.OpenInterceptor;
import net.fnsco.freamwork.interceptor.WebInterceptor;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    @Bean
    WebInterceptor localInterceptor() {
        return new WebInterceptor();
    }

    @Bean
    AppInterceptor appInterceptor() {
        return new AppInterceptor();
    }

    @Bean
    OpenInterceptor openInterceptor() {
        return new OpenInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localInterceptor()).addPathPatterns("/web/**");
        registry.addInterceptor(appInterceptor()).addPathPatterns("/app/**");
        registry.addInterceptor(openInterceptor()).addPathPatterns("/open/**");
    }
    //    @Override
    //    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
    //        registry.addResourceHandler("/*.js/**").addResourceLocations("/ui/static/");
    //        registry.addResourceHandler("/*.css/**").addResourceLocations("/ui/static/");
    //    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        super.configureMessageConverters(converters);
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
        fastConverter.setFastJsonConfig(fastJsonConfig);
        converters.add(fastConverter);
    }
}

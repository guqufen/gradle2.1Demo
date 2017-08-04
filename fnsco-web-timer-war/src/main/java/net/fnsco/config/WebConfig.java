package net.fnsco.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import net.fnsco.freamwork.interceptor.AppInterceptor;
import net.fnsco.freamwork.interceptor.OpenInterceptor;
import net.fnsco.freamwork.interceptor.WebInterceptor;

@Configuration
//@EnableRedisHttpSession
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

//    @Bean
//    public JedisConnectionFactory connectionFactory() {
//        return new JedisConnectionFactory();
//    }
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

//    @Override
//    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//        super.configureMessageConverters(converters);
//        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
//        FastJsonConfig fastJsonConfig = new FastJsonConfig();
//        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat,SerializerFeature.WriteMapNullValue);
//        fastConverter.setFastJsonConfig(fastJsonConfig);
//        converters.add(fastConverter);
//    }
    //    @Bean
    //    public RequestMappingHandlerAdapter requestMappingHandlerAdapter() {
    //        RequestMappingHandlerAdapter adapter = new RequestMappingHandlerAdapter();
    //        List converterList = Lists.newArrayList();
    //        FastJsonHttpMessageConverter fastJsonConverter = new FastJsonHttpMessageConverter();
    //        //FastJsonConfig fastJsonConfig = new FastJsonConfig();
    //        //SerializeConfig serializeConfig = new SerializeConfig();
    //
    //        //fastJsonConfig.setSerializeConfig(serializeConfig);
    //        //fastJsonConverter.setFastJsonConfig(fastJsonConfig);
    //        fastJsonConverter.setFeatures(SerializerFeature.WriteMapNullValue,
    //            SerializerFeature.WriteNullStringAsEmpty);
    //
    //        converterList.add(fastJsonConverter);
    //        converterList.add(new ByteArrayHttpMessageConverter());
    //        StringHttpMessageConverter stringConverter = new StringHttpMessageConverter();
    //        List<MediaType> supportedMediaTypes = Lists.newArrayList();
    //        MediaType type = MediaType.parseMediaType("text/html;charset=UTF-8");
    //        supportedMediaTypes.add(type);
    //        stringConverter.setSupportedMediaTypes(supportedMediaTypes);
    //        converterList.add(stringConverter);
    //        adapter.setMessageConverters(converterList);
    //        return adapter;
    //    }
}

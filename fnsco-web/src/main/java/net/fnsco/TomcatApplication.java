package net.fnsco;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import net.fnsco.config.TimerConfig;
import net.fnsco.config.WebConfig;
import net.fnsco.freamwork.log.filter.WebAccessLogFilter;

@SpringBootApplication
@ComponentScan("net.fnsco")
@EntityScan("net.fnsco.service.dao")
@Import({ TimerConfig.class, WebConfig.class })
public class TomcatApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(TomcatApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(TomcatApplication.class, args);
    }

    @Bean
    public FilterRegistrationBean registFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new WebAccessLogFilter());
        registration.addUrlPatterns("/*");
        //registration.addInitParameter("paramName", "paramValue");
        registration.setName("webAccessLogFilter");
        registration.setOrder(1);
        return registration;
    }

//    @Bean
//    public ServletListenerRegistrationBean servletListenerRegistrationBean() {
//        ServletListenerRegistrationBean servletListenerRegistrationBean = new ServletListenerRegistrationBean();
//        servletListenerRegistrationBean.setListener(new IndexListener());
//        return servletListenerRegistrationBean;
//    }

}
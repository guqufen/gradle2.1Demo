package net.fnsco;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import net.fnsco.freamwork.log.filter.WebAccessLogFilter;

//@SpringBootApplication
//@Import({ WebConfig.class })
public class Application {
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

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

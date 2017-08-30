package net.fnsco;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import net.fnsco.config.WebConfig;
import net.fnsco.freamwork.log.filter.WebAccessLogFilter;

@SpringBootApplication
@ComponentScan("net.fnsco")
@ComponentScan("net.fnsco")
@MapperScan({"net.fnsco.auth.service","net.fnsco.order.service","net.fnsco.bigdata.service"})
@Import({ WebConfig.class })
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
    //    AppAuthorizeFilter getAppAuthorizeFilter() {
    //        return new AppAuthorizeFilter();
    //    }
    //    @Bean  
    //    public FilterRegistrationBean  filterRegistrationBean() {  
    //        FilterRegistrationBean registrationBean = new FilterRegistrationBean();  
    //        registrationBean.setFilter(getAppAuthorizeFilter());  
    //        List<String> urlPatterns = new ArrayList<String>();  
    //        urlPatterns.add("/app/*");  
    //        registrationBean.setUrlPatterns(urlPatterns);  
    //        registrationBean.setOrder(2);
    //        return registrationBean;  
    //    }  
    //    @Bean
    //    public ServletListenerRegistrationBean servletListenerRegistrationBean() {
    //        ServletListenerRegistrationBean servletListenerRegistrationBean = new ServletListenerRegistrationBean();
    //        servletListenerRegistrationBean.setListener(new IndexListener());
    //        return servletListenerRegistrationBean;
    //    }

}
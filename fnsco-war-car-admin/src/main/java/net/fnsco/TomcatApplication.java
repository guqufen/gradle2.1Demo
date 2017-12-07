package net.fnsco;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
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
@MapperScan({ "net.fnsco.auth.service", "net.fnsco.order.service", "net.fnsco.bigdata.service", "net.fnsco.trading.service" })
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

    //    @Bean
    //    public EmbeddedServletContainerCustomizer containerCustomizer() {
    //
    //        return new EmbeddedServletContainerCustomizer() {
    //            @Override
    //            public void customize(ConfigurableEmbeddedServletContainer container) {
    //
    //                ErrorPage error401Page = new ErrorPage(HttpStatus.UNAUTHORIZED, "/401.html");
    //                ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/404.html");
    //                ErrorPage error500Page = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/500.html");
    //
    //                container.addErrorPages(error401Page, error404Page, error500Page);
    //            }
    //        };
    //    }
}
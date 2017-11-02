package net.fnsco.freamwork.swagger;

import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.async.DeferredResult;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * SwaggerConfig
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    /**
     * SpringBoot默认已经将classpath:/META-INF/resources/和classpath:/META-INF/resources/webjars/映射
     * 所以该方法不需要重写，如果在SpringMVC中，可能需要重写定义（我没有尝试）
     * 重写该方法需要 extends WebMvcConfigurerAdapter
     * 
     */
    //    @Override
    //    public void addResourceHandlers(ResourceHandlerRegistry registry) {
    //        registry.addResourceHandler("swagger-ui.html")
    //                .addResourceLocations("classpath:/META-INF/resources/");
    //
    //        registry.addResourceHandler("/webjars/**")
    //                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    //    }

    /**
     * 可以定义多个组，比如本类中定义把test和demo区分开了
     * （访问页面就可以看到效果了） 
     *
     */
    @Bean
    public Docket appApi() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("app").genericModelSubstitutes(DeferredResult.class)
            //                .genericModelSubstitutes(ResponseEntity.class)
            .useDefaultResponseMessages(false).forCodeGeneration(true).pathMapping("/")// base，最终调用接口后会和paths拼接在一起
            .select().paths(or(regex("/app/.*")))//过滤的接口
            .build().apiInfo(testApiInfo());
    }

    @Bean
    public Docket webApi() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("web").genericModelSubstitutes(DeferredResult.class)
            //              .genericModelSubstitutes(ResponseEntity.class)
            .useDefaultResponseMessages(false).forCodeGeneration(false).pathMapping("/")//base
            .select().paths(or(regex("/web/.*")))//过滤的接口
            .build().apiInfo(demoApiInfo());
    }

    @Bean
    public Docket openApi() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("open").genericModelSubstitutes(DeferredResult.class)
            //              .genericModelSubstitutes(ResponseEntity.class)
            .useDefaultResponseMessages(false).forCodeGeneration(false).pathMapping("/")//base
            .select().paths(or(regex("/open/.*")))//过滤的接口
            .build().apiInfo(demoApiInfo());
    }
    @Bean
    public Docket mobileApi() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("mobile").genericModelSubstitutes(DeferredResult.class)
            //              .genericModelSubstitutes(ResponseEntity.class)
            .useDefaultResponseMessages(false).forCodeGeneration(false).pathMapping("/")//base
            .select().paths(or(regex("/mobile/.*")))//过滤的接口
            .build().apiInfo(demoApiInfo());
    }
    @Bean
    public Docket h5Api() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("h5").genericModelSubstitutes(DeferredResult.class)
            //              .genericModelSubstitutes(ResponseEntity.class)
            .useDefaultResponseMessages(false).forCodeGeneration(false).pathMapping("/")//base
            .select().paths(or(regex("/h5/.*")))//过滤的接口
            .build().apiInfo(demoApiInfo());
    }
    private ApiInfo testApiInfo() {
        return new ApiInfoBuilder().title("给APP用的接口")//大标题
            .description("")//详细描述
            .version("1.0")//版本
            .termsOfServiceUrl("").contact(new Contact("宋先飞", "", "songfeixian@qq.com"))//作者
            .license("").licenseUrl("").build();
    }

    private ApiInfo demoApiInfo() {
        return new ApiInfoBuilder().title("后台管理功能API")//大标题
            .description("")//详细描述
            .version("1.0")//版本
            .termsOfServiceUrl("").contact(new Contact("宋先飞", "", "songfeixian@qq.com"))//作者
            .license("").licenseUrl("").build();

    }
}

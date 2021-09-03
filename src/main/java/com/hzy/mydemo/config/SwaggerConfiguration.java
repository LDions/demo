package com.hzy.mydemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
//@Profile(JHipsterConstants.SPRING_PROFILE_SWAGGER) //加上这个运行项目doc.html文档是空白的？？？
@EnableSwagger2 //swagger的开关，表示已经启用swagger
public class SwaggerConfiguration { //配置swagger的启用配置文件，支持接口分组的配置，如果没有分组只需要创建一个Docket即可，不需要再在application.yml文件中配置swagger相关的，注释掉默认的

    /** 给接口分组
     * RequestHandlerSelectors类的方法：
     * 　　Predicate<RequestHandler> any()：返回包含所有满足条件的请求处理器的断言，该断言总为true
     * 　　Predicate<RequestHandler> none()：返回不满足条件的请求处理器的断言,该断言总为false
     * 　　Predicate<RequestHandler> basePackage(final String basePackage)：返回一个断言(Predicate)，该断言包含所有匹配basePackage下所有类的请求路径的请求处理器
     * PathSelectors类的方法：
     * 　　Predicate<String> any():满足条件的路径，该断言总为true
     * 　　Predicate<String> none():不满足条件的路径,该断言总为false  (生产环境可以屏蔽掉swagger：https://blog.csdn.net/goldenfish1919/article/details/78280051)
     * 　　Predicate<String> regex(final String pathRegex):符合正则的路径
     *
     * @return
     */
    @Bean
    public Docket createOriginalApi() {
        return new Docket(DocumentationType.SWAGGER_2)
            .apiInfo(apiInfo())
            .groupName("初始化接口文档")
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.hzy.mydemo.modules.firstversion.rest")) //添加路径选择条件
            .paths(PathSelectors.any()) //设置路径筛选
            .build();
    }

    /**
     * 创建API应用
     * apiInfo() 增加API相关信息
     * 通过select()函数返回一个ApiSelectorBuilder实例，用来控制哪些接口暴露给swagger来展示
     *
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
            .title("HZY的API文档")
            .description("使用swagger2构建RESTful APIs")
            .contact(new Contact("韩宗晏", "", "2694166036@qq.com"))
            .version("1.0")
            .build();
    }

    @Bean
    public Docket createTestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
            .apiInfo(apiInfo())
            .groupName("测试接口文档")
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.hzy.mydemo.modules.test.rest")) //添加路径选择条件
            .paths(PathSelectors.any()) //设置路径筛选
            .build();
    }
}

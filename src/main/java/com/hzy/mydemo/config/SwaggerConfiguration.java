package com.hzy.mydemo.config;

import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import io.github.jhipster.config.JHipsterConstants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.RequestHandler;
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

    private static final String SPLITOR = ";";

    /**
     * 新添加的包(不包含common中的)放在这里，
     * 下面的逻辑会根据路由中携带的信息判断是小程序还是后台！！！
     */
    private static final String HZY_PACKAGE =
        "com.hzy.mydemo.modules.basic.web.rest" + SPLITOR + "com.hzy.mydemo.modules.verification.rest";

    /**
     * 给接口分组
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
            //            .groupName("初始化接口文档")
            .select()
            //            .apis(RequestHandlerSelectors.basePackage("com.hzy.mydemo.modules.firstversion.rest")) //添加路径选择条件
            .apis(basePackage("com.hzy.mydemo.modules.login.rest" + SPLITOR + "com.hzy.mydemo.modules.firstversion.rest"))
            .paths(PathSelectors.any()) //设置路径筛选
            .build()
            .pathMapping("/")
            .groupName("初始化接口文档");
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
    public Docket createBasicApi() {
        return new Docket(DocumentationType.SWAGGER_2)
            .apiInfo(apiInfo())
            .groupName("基础接口文档")
            .select()
            //            .apis(RequestHandlerSelectors.basePackage("com.hzy.mydemo.modules.test.rest")) //添加路径选择条件
            .apis(basePackage(HZY_PACKAGE))
            .paths(PathSelectors.any()) //设置路径筛选
            .build()
            .pathMapping("/");
    }

    public static Predicate<RequestHandler> basePackage(final String basePackage) {
        return input -> declaringClass(input).transform(handlerPackage(basePackage)).or(true);
    }

    private static Function<Class<?>, Boolean> handlerPackage(final String basePackage) {
        return input -> {
            // 循环判断匹配
            for (String strPackage : basePackage.split(SPLITOR)) {
                boolean isMatch = input.getPackage().getName().startsWith(strPackage);
                if (isMatch) {
                    return true;
                }
            }
            return false;
        };
    }

    private static Optional<? extends Class<?>> declaringClass(RequestHandler input) {
        return Optional.fromNullable(input.declaringClass());
    }
    /**
     * 切割扫描的包生成Predicate
     *
     * @param basePackage
     * @return
     */
    //    public static Predicate scanBasePackage(final String basePackage) {
    //        if (StringUtils.isBlank(basePackage))
    //
    //            throw new NullPointerException("basePackage不能为空，多个包扫描使用" + SPLITOR + "分隔");
    //
    //        String[] controllerPack = basePackage.split(SPLITOR);
    //
    //        Predicate predicate = null;
    //
    //        for (int i = controllerPack.length - 1; i >= 0; i--) {
    //            String strBasePackage = controllerPack[i];
    //
    //            if (StringUtils.isNotBlank(strBasePackage)) {
    //                Predicate secPredicate = (Predicate) RequestHandlerSelectors.basePackage(strBasePackage);
    //
    //                predicate = predicate == null ? secPredicate : Predicates.or(secPredicate, predicate);
    //
    //            }
    //
    //        }
    //
    //        if (predicate == null)
    //
    //            throw new NullPointerException("basePackage配置不正确，多个包扫描使用" + SPLITOR + "分隔");
    //
    //        return predicate;
    //
    //    }
}

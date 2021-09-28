package com.hzy.mydemo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Properties specific to Demo.
 * <p>
 * Properties are configured in the {@code application.yml} file.
 * See {@link tech.jhipster.config.JHipsterProperties} for a good example.
 */

//通过该注解可以获取到application.yml中的参数值 我们为属性配置错误的值时，而又不希望 Spring Boot 应用启动失败【默认情况会启动失败并抛异常】，我们可以设置 ignoreInvalidFields 属性为 true (默认为 false)
//默认情况下，Spring Boot 会忽略那些不能绑定到 @ConfigurationProperties 类字段的属性 然而，当配置文件中有一个属性实际上没有绑定到 @ConfigurationProperties 类时，我们可能希望启动失败。
//也许我们以前使用过这个配置属性，但是它已经被删除了，这种情况我们希望被触发告知手动从 application.properties 删除这个属性为了实现上述情况，我们仅需要将 ignoreUnknownFields 属性设置为 false (默认是 true)
//添加@Component注解让其被扫描到才会生效？
@ConfigurationProperties(prefix = "application", ignoreUnknownFields = false)
public class ApplicationProperties {}

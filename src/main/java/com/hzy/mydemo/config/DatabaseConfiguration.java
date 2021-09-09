package com.hzy.mydemo.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import javax.persistence.EntityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/*
在Springboot应用开发中使用JPA时，通常在主应用程序所在包或者其子包的某个位置定义我们的Entity和Repository，这样基于Springboot的自动配置，
无需额外配置，我们定义的Entity和Repository即可被发现和使用。但有时候我们需要定义Entity和Repository不在应用程序所在包及其子包，
那么这时候就需要使用@EntityScan和@EnableJpaRepositories了。
 */
@Configuration
@EnableJpaRepositories("com.hzy.mydemo.modules.**.repository") // 用来扫描和发现指定包及其子包中的Repository定义 用于Srping JPA的代码配置，用于取代xml形式的配置文件
@EnableJpaAuditing(auditorAwareRef = "springSecurityAuditorAware")
@EnableTransactionManagement
public class DatabaseConfiguration {

    @Bean
    public JPAQueryFactory jpaQueryFactory(EntityManager entityManager) {
        return new JPAQueryFactory(entityManager);
    }
}

package com.hzy.mydemo.config;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * @description: 调用第三方接口使用（同httpURLConnection类似）
 * @author: hzy
 * @time: 2021/9/23 15:58
 */
@Configuration
public class RestTemplateConfiguration {

    @Bean("restTemplate") //加上不同的名字让spring识别
    @Primary //用该注解告诉spring在犹豫的时候优先选择哪个具体的实现，自动装配时当出现多个Bean候选者时，被注解为@Primary的Bean将作为首选者 具体实现用@Qualifier("longRestTemplate")选择使用哪个
    public RestTemplate restTemplate() {
        return new RestTemplate(getClientHttpRequestFactory());
    }

    private ClientHttpRequestFactory getClientHttpRequestFactory() {
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        // 设置整个连接池最大连接数
        connectionManager.setMaxTotal(500);
        // 路由是对maxTotal的细分
        connectionManager.setDefaultMaxPerRoute(500);

        RequestConfig requestConfig = RequestConfig
            .custom()
            .setConnectionRequestTimeout(5000)
            .setConnectTimeout(5000)
            .setSocketTimeout(5000)
            .build();

        CloseableHttpClient client = HttpClientBuilder
            .create()
            .setDefaultRequestConfig(requestConfig)
            .setConnectionManager(connectionManager)
            .build();

        return new HttpComponentsClientHttpRequestFactory(client);
    }

    @Bean("longRestTemplate")
    public RestTemplate longRestTemplate() {
        return new RestTemplate(getLongClientHttpRequestFactory());
    }

    private ClientHttpRequestFactory getLongClientHttpRequestFactory() {
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        // 设置整个连接池最大连接数
        connectionManager.setMaxTotal(50);
        // 路由是对maxTotal的细分
        connectionManager.setDefaultMaxPerRoute(50);

        RequestConfig requestConfig = RequestConfig
            .custom()
            .setConnectionRequestTimeout(5 * 60 * 1000)
            .setConnectTimeout(5 * 60 * 1000)
            .setSocketTimeout(5 * 60 * 1000)
            .build();

        CloseableHttpClient client = HttpClientBuilder
            .create()
            .setDefaultRequestConfig(requestConfig)
            .setConnectionManager(connectionManager)
            .build();

        return new HttpComponentsClientHttpRequestFactory(client);
    }
}

package com.hzy.mydemo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

/**
 * @description:
 * @author: hzy
 * @time: 2021/9/26 9:04
 */
@Configuration
public class RedisConfiguration {

    @Autowired
    public final RedisConnectionFactory redisConnectionFactory;

    public RedisConfiguration(RedisConnectionFactory redisConnectionFactory) {
        this.redisConnectionFactory = redisConnectionFactory;
    }

    /**
     * 监听redis的过期事件
     *
     * @return
     */
    @Bean
    public RedisMessageListenerContainer redisMessageListenerContainer() {
        RedisMessageListenerContainer redisMessageListenerContainer = new RedisMessageListenerContainer();
        redisMessageListenerContainer.setConnectionFactory(redisConnectionFactory);
        return redisMessageListenerContainer;
    }

    @Bean
    public KeyExpirationEventMessageListener keyExpirationEventMessageListener() {
        return new KeyExpirationEventMessageListener(this.redisMessageListenerContainer());
    }
}

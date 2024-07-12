package com.deng.springbootinit.config;

import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;

/**
 * 缓存配置类
 */
@Configuration
public class CacheConfig {
    /**
     * redis缓存管理器
     */
    @Bean
    public CacheManager redisCacheManager(RedisConnectionFactory connectionFactory){
        RedisCacheWriter redisCacheWriter = RedisCacheWriter.nonLockingRedisCacheWriter(connectionFactory);
        RedisCacheConfiguration defaultCacheConfig = RedisCacheConfiguration.defaultCacheConfig()
                .disableCachingNullValues()//禁用空值缓存
                .prefixCacheNameWith("Cache::Novel::");//设置缓存前缀
//TODO 循环设置TTL
        RedisCacheManager redisCacheManager = new RedisCacheManager(redisCacheWriter
                , defaultCacheConfig);
        redisCacheManager.setTransactionAware(true);
        redisCacheManager.initializeCaches();
        return redisCacheManager;
    }
}

package com.aicode.ratelimter.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * Redisson配置类，用于配置和创建Redisson客户端Bean
 * 通过该类可以配置Redisson连接Redis服务器的各项参数
 */
@Configuration
public class RedissonConfig {

    @Value("${spring.data.redis.host}")
    private String redisHost;

    @Value("${spring.data.redis.port}")
    private Integer redisPort;

    @Value("${spring.data.redis.password}")
    private String redisPassword;

    @Value("${spring.data.redis.database}")
    private Integer redisDatabase;

/**
 * 配置并创建Redisson客户端Bean
 * @return 返回配置好的RedissonClient实例
 */
    @Bean
    public RedissonClient redissonClient() {
        Config config = new Config();
    // 构建Redis服务器地址
        String address = "redis://" + redisHost + ":" + redisPort;
    // 配置单服务器模式
        SingleServerConfig singleServerConfig = config.useSingleServer()
            // 设置Redis服务器地址
                .setAddress(address)
            // 设置Redis数据库索引（默认为0）
                .setDatabase(redisDatabase)
            // 设置连接池中最小空闲连接数
                .setConnectionMinimumIdleSize(1)
            // 设置连接池最大连接数
                .setConnectionPoolSize(10)
            // 设置连接池最大空闲时间（毫秒）
                .setIdleConnectionTimeout(30000)
            // 设置连接超时时间（毫秒）
                .setConnectTimeout(5000)
            // 设置命令等待超时时间（毫秒）
                .setTimeout(3000)
            // 设置命令失败重试次数
                .setRetryAttempts(3)
            // 设置命令重试间隔时间（毫秒）
                .setRetryInterval(1500);
        // 如果有密码则设置密码
        if (redisPassword != null && !redisPassword.isEmpty()) {
            singleServerConfig.setPassword(redisPassword);
        }
        return Redisson.create(config);
    }
}
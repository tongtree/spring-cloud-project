package com.tong.consume.config;

import org.apache.commons.lang3.StringUtils;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.StringCodec;
import org.redisson.config.ClusterServersConfig;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @author niutong
 */
@Configuration
public class RedissonConfig {

    @Value("${redis.server.pattern:single}")
    private String pattern;

    @Value("${redis.server.host:127.0.0.1}")
    private String host;

    @Value("${redis.server.nodes}")
    private List<String> nodes;

    @Value("${redis.server.port:6379}")
    private String port;

    @Value("${redis.server.password}")
    private String password;

    @Value("${redis.server.database:1}")
    private Integer database;


    @Bean
    @ConditionalOnProperty(prefix = "redis.server", name = "pattern", havingValue = "cluster")
    public RedissonClient clusterRedisClient() {
        Config config = new Config();
        ClusterServersConfig clusterServersConfig = config.useClusterServers();

        if (!StringUtils.isBlank(password)) {
            clusterServersConfig.setPassword(password);
        }
        // 集群状态扫描间隔时间，单位是毫秒
        clusterServersConfig.setScanInterval(2000);
        for (String node : nodes) {
            String url = "redis://" + node;
            clusterServersConfig.addNodeAddress(url);
        }
        RedissonClient redissonClient = Redisson.create(config);

        return redissonClient;
    }


    @Bean
    @ConditionalOnProperty(prefix = "redis.server", name = "pattern", havingValue = "single")
    public RedissonClient singleRedisClient() {
        Config config = new Config();
        SingleServerConfig singleServerConfig = config.useSingleServer();

        if (!StringUtils.isBlank(password)) {
            singleServerConfig.setPassword(password);
        }
        String url = "redis://" + host + ":" + port;
        singleServerConfig.setAddress(url);
        singleServerConfig.setDatabase(database);
        RedissonClient redissonClient = Redisson.create(config);

        return redissonClient;
    }
}
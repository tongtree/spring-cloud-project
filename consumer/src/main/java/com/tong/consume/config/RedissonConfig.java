package com.tong.consume.config;

import org.apache.commons.lang3.StringUtils;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.ClusterServersConfig;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @author niutong
 */
@Configuration
public class RedissonConfig {


    @Value("${spring.redis.cluster.nodes}")
    private List<String> nodes;

    @Value("${spring.redis.password}")
    private String password;

    @Value("${spring.redis.database}")
    private Integer database;


    @Bean
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


}
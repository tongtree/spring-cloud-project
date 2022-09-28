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

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private String port;

    @Value("${spring.redis.password}")
    private String password;

    @Value("${redis.cluster.nodes}")
    private List<String> nodes;


    @Bean
    @ConditionalOnProperty(prefix = "spring.redis", name = "pattern", havingValue = "cluster")
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

        return Redisson.create(config);
    }



    @Bean
    @ConditionalOnProperty(prefix = "spring.redis", name = "pattern", havingValue = "single")
    public RedissonClient singleRedisClient() {
        Config config = new Config();
        SingleServerConfig singleServerConfig = config.useSingleServer();

        if (!StringUtils.isBlank(password)) {
            singleServerConfig.setPassword(password);
        }
        String url = "redis://" + host + ":" + port;
        singleServerConfig.setAddress(url);
        singleServerConfig.setDatabase(0);

        return Redisson.create(config);
    }

}
package com.tong.consume.controller;

import com.alibaba.fastjson.JSON;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


/**
 * // topic需要和生产者的topic一致，consumerGroup属性是必须指定的，内容可以随意
 * // selectorExpression的意思指的就是tag，默认为“*”，不设置的话会监听所有消息
 * // MessageExt：是一个消息接收通配符，不管发送的是String还是对象，都可接收，当然也可以像上面明确指定类型
 */
@Component
@RocketMQMessageListener(consumerGroup = "${rocketmq.producer.group}",
        topic = "${rocketmq.producer.topic}",
        selectorExpression = "*")
public class RocketMqConsume implements RocketMQListener<String> {

    private static final Logger log = LoggerFactory.getLogger(RocketMqConsume.class);

    @Override
    public void onMessage(String msg) {
        log.info("监听到消息：messageExt={}", msg);
    }
}

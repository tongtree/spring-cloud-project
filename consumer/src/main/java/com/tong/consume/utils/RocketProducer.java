package com.tong.consume.utils;

import com.alibaba.fastjson.JSON;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author Admin
 */
@Component
public class RocketProducer {
    @Resource
    private RocketMQTemplate rocketMqTemplate;

    @Value("${rocketmq.producer.topic}")
    private String topic;


    @Value("${rocketmq.producer.send-message-timeout}")
    private Long messageTimeOut;


    /**
     * 发送同步消息（阻塞当前线程，等待broker响应发送结果，这样不太容易丢失消息）
     * （msgBody也可以是对象，sendResult为返回的发送结果）
     */
    public SendResult sendMsg(String msgBody) {
        SendResult sendResult = rocketMqTemplate.syncSend(topic, MessageBuilder.withPayload(msgBody).build());
        return sendResult;
    }

    /**
     * 发送带tag同步消息
     */
    public SendResult sendTagMessage(String tag, Object body, String key, String name) {
        Message<Object> message = MessageBuilder.withPayload(body)
                .setHeader("key", key)
                .setHeader("authUser", name).build();
        return rocketMqTemplate.syncSend(topic + ":" + tag, message);
    }


    /**
     * 发送异步消息（通过线程池执行发送到broker的消息任务，执行完后回调：在SendCallback中可处理相关成功失败时的逻辑）
     * （适合对响应时间敏感的业务场景）
     */
    public void sendAsyncMsg(String msgBody,SendCallback callback) {
        rocketMqTemplate.asyncSend(topic, MessageBuilder.withPayload(msgBody).build(), callback);
    }

    /**
     * 发送延时消息（上面的发送同步消息，delayLevel的值就为0，因为不延时）
     * 在start版本中 延时消息一共分为18个等级分别为：1s 5s 10s 30s 1m 2m 3m 4m 5m 6m 7m 8m 9m 10m 20m 30m 1h 2h
     */
    public void sendDelayMsg(String msgBody, int delayLevel) {
        rocketMqTemplate.syncSend(topic, MessageBuilder.withPayload(msgBody).build(), messageTimeOut, delayLevel);
    }

    /**
     * 发送单向消息（只负责发送消息，不等待应答，不关心发送结果，如日志）
     */
    public void sendOneWayMsg(String msgBody) {
        rocketMqTemplate.sendOneWay(topic, MessageBuilder.withPayload(msgBody).build());
    }

}


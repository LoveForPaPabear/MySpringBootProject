package com.example.myspringproject.rocketmq.producer;

import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.messaging.support.MessageBuilder;

import java.math.BigDecimal;

/**
 * @Author ppbear xuzheng/ppbeartoxuzheng@163.com
 * @Description rocketmq 配置生产者
 * @Date 14:45 2019/7/1
 * @Param
 * @return
 **/
public class Producer implements CommandLineRunner {
    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @Override
    public void run(String... args) {
        rocketMQTemplate.convertAndSend("test-topic-1", "Hello, World!");
        rocketMQTemplate.send("test-topic-1", MessageBuilder.withPayload("Hello, World! I'm from spring message").build());
        rocketMQTemplate.convertAndSend("test-topic-2", new OrderPaidEvent("T_001", new BigDecimal("88.00")));
    }
}


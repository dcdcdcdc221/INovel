package com.deng.springbootinit.controller;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("/test")
public class NormalProduceController {
    @Resource
    RocketMQTemplate rocketMQTemplate;




    @GetMapping("/test")
    public void noTag() {
        // convertAndSend() 发送普通字符串消息
        rocketMQTemplate.convertAndSend("sendMessage_topic", "Hello Word");
    }
}

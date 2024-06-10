package com.example.redisstudy;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;

@Service
public class RedisPublisher {
    // topic == Channel
    // 특정 채널에 메시지 전송

    private final RedisTemplate<String, Object> template;

    public RedisPublisher(RedisTemplate<String, Object> template) {
        this.template = template;
    }

    /*
     * Object Publish
     */
    public void publish(ChannelTopic topic, MessageDTO dto) {
        template.convertAndSend(topic.getTopic(), dto);
    }

    /*
     * String Publish
     */
    public void  publish(ChannelTopic topic, String data) {
        template.convertAndSend(topic.getTopic(), data);
    }


}

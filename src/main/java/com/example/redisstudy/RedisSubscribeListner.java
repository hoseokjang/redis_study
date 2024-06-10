package com.example.redisstudy;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class RedisSubscribeListner implements MessageListener {
    // 전송된 메시지 받기
    // pub 된 메시지가 존재할 때, converting, 로깅, DB 저장 등등을 수행하기위한 class

    private final RedisTemplate<String, Object> template;
    private final ObjectMapper objectMapper;

    @Override
    public void onMessage(Message message, byte[] pattern) {
        try {
            String publishMessage = template
                    .getStringSerializer().deserialize(message.getBody());

            MessageDTO messageDTO = objectMapper.readValue(publishMessage, MessageDTO.class);

            log.info("Redis Subscribe Channel : " + messageDTO.getRoomId());
            log.info("Redis Sub Message : {}", publishMessage);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
        }
    }
}

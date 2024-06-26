package com.example.redisstudy;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/redis/pubsub")
public class RedisPubSubController {

    private final RedisPubService redisPubService;

    @PostMapping("/send")
    public void sendMessage(@RequestParam(required = true) String channel, @RequestBody MessageDTO message) {
        // 메시지 저장
        log.info("Redis pub MSG Channel = {}", channel);
        redisPubService.pubMsgChannel(channel, message);
    }

    @PostMapping("/cancel")
    public void cancelSubChannel(@RequestParam String channel) {
        // 구독 취소
        redisPubService.cancelSubChannel(channel);
    }

}

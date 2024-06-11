package com.example.redisstudy.user;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash(value = "user", timeToLive = 60) // timeToLive 옵션으로 데이터의 만료 시간 설정 가능
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    private String id;
    private String name;
    private String major;
    private int age;
}

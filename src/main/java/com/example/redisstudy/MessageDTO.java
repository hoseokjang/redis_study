package com.example.redisstudy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageDTO implements Serializable {
    // 메시지 전송 요청을 Redis에 전송하는 역할

    private static final long serialVersionUID = 1L;

    private String message; // 전송할 메시지 내용
    private String sender; // 메시지 발신자
    private String roomId; // 메시지 방 번호 or 타겟 채널

}

package com.example.redisstudy.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @PostMapping("/redis/v1/post")
    public User addUser(@RequestBody User user) {
        // redis에 User 정보 저장
        log.info("Controller Request : {}", user);

        User result = userService.addUser(user);

        log.info("Controller result : {}", result);

        return result;
    }

    @PostMapping("/redis/v1/getUser")
    public User getUser(@RequestParam String reqId) {
        User user = userService.getUserById(reqId);
        return user;
    }
}

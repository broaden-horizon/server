package com.example.server.controller;

import com.example.server.dto.Req;
import com.example.server.dto.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping("/api/server")
public class HelloController {

    @GetMapping("/hello")
    public User hello(@RequestParam(required = false) String id, @RequestParam String email, @RequestParam String phoneNumber) {
        User user = new User();
        user.setId(id);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        return user;
    }

    @PostMapping("/bye/name/{name}")
    public User bye(@PathVariable String name, @RequestBody User user) {
        System.out.println(user);
        System.out.println("server response, name: " + name);
        return user;
    }
    //http://localhost:9090/api/server/post/exchange/{name}/{userId}
    @PostMapping("/post/exchange/{name}/{userId}")
    public Req<User> postExchange(@RequestBody Req<User> user,
                             @PathVariable String name,
                             @PathVariable String userId,
                             @RequestHeader("x-authorization") String authorization,
                             @RequestHeader("custom-header") String customHeader
    ) {
        System.out.println("server took the request");
        log.info("userId: {}, userName: {}", userId, name);
        log.info("auto: {}, custom: {}", authorization, customHeader);
        log.info("client req: {}", user);

        return user;
    }
}

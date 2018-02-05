package com.vg.cube;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@RequestMapping("/api")
//@RefreshScope
public class CubeWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(CubeWebApplication.class, args);
    }

    @RequestMapping("/ping")
    public String ping() {
        return "App's Up";
    }

}
/*
@RefreshScope
@RestController
class MessageRestController {

    @Value("${app.global.message}")
    private String message;

    @RequestMapping("/message")
    String getMessage() {
        return this.message;
    }
}*/

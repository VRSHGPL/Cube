package com.vg.cube;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@RequestMapping("/api")
@ComponentScan("com.vg.*")
@CrossOrigin(origins = { "http://localhost:4200"})
public class CubeWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(CubeWebApplication.class, args);
    }

    @RequestMapping("/ping")
    public String ping() {
        System.out.println("********************************************************");
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

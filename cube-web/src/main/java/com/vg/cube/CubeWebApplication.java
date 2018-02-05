package com.vg.cube;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vg.cube.config.ServerConfig;

@SpringBootApplication
@RestController
@RefreshScope
@RequestMapping("/api")
public class CubeWebApplication {

    @Autowired
    private ServerConfig serverConfig;

    public static void main(String[] args) {
        SpringApplication.run(CubeWebApplication.class, args);
    }

    @RequestMapping("/ping")
    public String ping() {
        return "App's Up";
    }

    @RequestMapping(value = "/config", method = RequestMethod.GET)
    public String getMessage() {
        return serverConfig.getGlobalMessage() + " --" + serverConfig.getLocalMessage();
    }

}

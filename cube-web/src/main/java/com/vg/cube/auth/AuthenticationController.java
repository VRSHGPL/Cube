package com.vg.cube.auth;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vg.cube.model.User;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public User auth(@RequestParam(value = "userName", required = true) String userName,
            @RequestParam(value = "password", required = true) String password) {
        /**
         * consider authenticated
         * 
         * TODO: persist / retrieve from dynamodb
         */
        return new User(userName, password);
    }

}

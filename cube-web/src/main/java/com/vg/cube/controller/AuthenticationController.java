package com.vg.cube.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vg.cube.model.User;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

	private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationController.class);

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public User auth(@RequestParam(value = "userName", required = true) String userName,
			@RequestParam(value = "password", required = true) String password) {
		/**
		 * consider authenticated
		 * 
		 * TODO: persist / retrieve from DYNAMODB
		 */
		LOGGER.info("Pseudo Authentication Performed");
		return new User(userName, password);
	}

}

package com.hkt.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hkt.user.dto.UserDto;
import com.hkt.user.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	UserService userService;
	
	@ResponseBody
	@RequestMapping(value = "/register.service", method = { RequestMethod.POST })
	public UserDto register(@RequestHeader HttpHeaders headers, @RequestBody UserDto user) {
		return userService.register(user);
	}
	
	@ResponseBody
	@RequestMapping(value = "/login.service", method = { RequestMethod.POST })
	public UserDto login(@RequestHeader HttpHeaders headers, @RequestBody UserDto user) {
		return userService.login(user);
	}
}

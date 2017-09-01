package com.hackathon.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GatewayController {
	
	@ResponseBody
	@RequestMapping(value = "/testPrint.service", method = { RequestMethod.POST })
	public String testPrint(){
		System.out.println("Hello");
		return "Hello";
	}
}


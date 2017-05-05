package com.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("test")
public class HelloController {
	@RequestMapping(value="abc",method=RequestMethod.GET)
	@ResponseBody
	public String hello(){
		return "Hello world:";
	}
}

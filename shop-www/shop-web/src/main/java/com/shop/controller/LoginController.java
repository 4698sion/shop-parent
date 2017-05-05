package com.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shop.core.base.BaseController;

@Controller
public class LoginController extends BaseController{
	
	@RequestMapping("login")
	public String login (String redirectUrl,Model model){
		
		model.addAttribute("redirectUrl",redirectUrl);
		return "user/login";
	}
	
	@RequestMapping("register")
	public String register(String redirectUrl,Model model){
		model.addAttribute("redirectUrl",redirectUrl);
		return "user/register";
	}
}

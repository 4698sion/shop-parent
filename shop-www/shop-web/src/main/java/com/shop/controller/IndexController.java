package com.shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shop.core.base.BaseController;
import com.shop.service.IndexService;

@Controller
public class IndexController extends BaseController {
	
	@Autowired
	private IndexService indexService;
	
	@RequestMapping("index")
	public String index(Model model) {
		List<String> keywords = indexService.findHotSearchKeys();
		model.addAttribute("keywords", keywords);
		return "index";
	}
	
}
